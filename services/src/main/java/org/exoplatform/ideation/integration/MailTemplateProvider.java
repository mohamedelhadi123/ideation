package org.exoplatform.ideation.integration;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.annotation.TemplateConfig;
import org.exoplatform.commons.api.notification.annotation.TemplateConfigs;
import org.exoplatform.commons.api.notification.channel.template.AbstractTemplateBuilder;
import org.exoplatform.commons.api.notification.channel.template.TemplateProvider;
import org.exoplatform.commons.api.notification.model.MessageInfo;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.api.notification.plugin.config.PluginConfig;
import org.exoplatform.commons.api.notification.service.setting.PluginSettingService;
import org.exoplatform.commons.api.notification.service.template.TemplateContext;
import org.exoplatform.commons.notification.template.DigestTemplate.ElementType;
import org.exoplatform.commons.notification.template.TemplateUtils;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.commons.utils.HTMLEntityEncoder;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.notification.LinkProviderUtils;
import org.gatein.common.text.EntityEncoder;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@TemplateConfigs(templates = {
        @TemplateConfig(pluginId = IdeaCommentedPlugin.ID, template = "war:/notification/template/mail/IdeaCommentedPlugin.gtmpl"),
        @TemplateConfig(pluginId = IdeaLikedPlugin.ID, template = "war:/notification/template/mail/IdeaLikedPlugin.gtmpl"),
        @TemplateConfig(pluginId = IdeaRatedPlugin.ID, template = "war:/notification/template/mail/IdeaRatedPlugin.gtmpl"),


})
public class MailTemplateProvider extends TemplateProvider {

    public MailTemplateProvider(InitParams initParams) {
        super(initParams);

        this.templateBuilders.put(PluginKey.key(IdeaCommentedPlugin.ID), new CommentTemplateBuilder());
        this.templateBuilders.put(PluginKey.key(IdeaLikedPlugin.ID), new LikeTemplateBuilder());
            this.templateBuilders.put(PluginKey.key(IdeaRatedPlugin.ID), new RateTemplateBuilder());


    }

    private class TemplateBuilder extends AbstractTemplateBuilder {
        @Override
        protected MessageInfo makeMessage(NotificationContext ctx) {
            EntityEncoder encoder = HTMLEntityEncoder.getInstance();

            NotificationInfo notification = ctx.getNotificationInfo();
            String language = getLanguage(notification);
            String creator = notification.getValueOwnerParameter(NotificationUtils.CREATOR);
            String ideaTitle = notification.getValueOwnerParameter(NotificationUtils.IDEA_TITLE);
            String ideaDesc = notification.getValueOwnerParameter(NotificationUtils.IDEA_DESCRIPTION);
            String commentText = notification.getValueOwnerParameter(NotificationUtils.COMMENT_TEXT);

            TemplateContext templateContext = new TemplateContext(notification.getKey().getId(), language);
            Identity author = CommonsUtils.getService(IdentityManager.class).getOrCreateIdentity(OrganizationIdentityProvider.NAME, creator, true);
            Profile profile = author.getProfile();
            //creator
            templateContext.put("USER", encoder.encode(profile.getFullName()));
            templateContext.put("AVATAR", LinkProviderUtils.getUserAvatarUrl(profile));
            templateContext.put("PROFILE_URL", LinkProviderUtils.getRedirectUrl("user", author.getRemoteId()));
            //receiver
            Identity receiver = CommonsUtils.getService(IdentityManager.class).getOrCreateIdentity(OrganizationIdentityProvider.NAME, notification.getTo(), true);
            templateContext.put("FIRST_NAME", encoder.encode(receiver.getProfile().getProperty(Profile.FIRST_NAME).toString()));
            //
            templateContext.put("IDEA_TITLE", encoder.encode(ideaTitle));
            templateContext.put("IDEA_DESCRIPTION", encoder.encode(getExcerpt(ideaDesc, 130)));
            templateContext.put("COMMENT_TEXT", commentText == null ? "" : encoder.encode(getExcerpt(commentText, 130)));

            //
            templateContext.put("FOOTER_LINK", LinkProviderUtils.getRedirectUrl("notification_settings", receiver.getRemoteId()));
            String subject = TemplateUtils.processSubject(templateContext);

            String body = TemplateUtils.processGroovy(templateContext);
            //binding the exception throws by processing template
            ctx.setException(templateContext.getException());
            MessageInfo messageInfo = new MessageInfo();
            return messageInfo.subject(subject).body(body).end();
        }

        @Override
        protected boolean makeDigest(NotificationContext ctx, Writer writer) {
            EntityEncoder encoder = HTMLEntityEncoder.getInstance();

            List<NotificationInfo> notifications = ctx.getNotificationInfos();
            NotificationInfo first = notifications.get(0);

            String language = getLanguage(first);
            TemplateContext templateContext = new TemplateContext(first.getKey().getId(), language);
            //
            Identity receiver = CommonsUtils.getService(IdentityManager.class).getOrCreateIdentity(OrganizationIdentityProvider.NAME, first.getTo(), true);
            templateContext.put("FIRST_NAME", encoder.encode(receiver.getProfile().getProperty(Profile.FIRST_NAME).toString()));
            templateContext.put("FOOTER_LINK", LinkProviderUtils.getRedirectUrl("notification_settings", receiver.getRemoteId()));

            try {
                writer.append(buildDigestMsg(notifications, templateContext));
            } catch (IOException e) {
                ctx.setException(e);
                return false;
            }
            return true;
        }

        protected String buildDigestMsg(List<NotificationInfo> notifications, TemplateContext templateContext) {
            EntityEncoder encoder = HTMLEntityEncoder.getInstance();

            Map<String, List<NotificationInfo>> map = new HashMap<String, List<NotificationInfo>>();


            StringBuilder sb = new StringBuilder();
            for (String activityID : map.keySet()) {
                List<NotificationInfo> notifs = map.get(activityID);
                NotificationInfo first = notifs.get(0);
                String ideaTitle = "";
                if (notifs.size() == 1) {
                    ideaTitle = first.getValueOwnerParameter(NotificationUtils.IDEA_TITLE);
                    templateContext.digestType(ElementType.DIGEST_ONE.getValue());
                } else {
                    templateContext.digestType(ElementType.DIGEST_MORE.getValue());
                }

                sb.append("<li style=\"margin:0 0 13px 14px;font-size:13px;line-height:18px;font-family:HelveticaNeue,Helvetica,Arial,sans-serif\"><div style=\"color: #333;\">");
                String digester = TemplateUtils.processDigest(templateContext);
                sb.append(digester);
                sb.append("</div></li>");
            }

            return sb.toString();
        }


    };

    private class CommentTemplateBuilder extends TemplateBuilder {
        protected String buildDigestMsg(List<NotificationInfo> notifications, TemplateContext templateContext) {
            EntityEncoder encoder = HTMLEntityEncoder.getInstance();

            Map<String, List<NotificationInfo>> map = new HashMap<String, List<NotificationInfo>>();

            StringBuilder sb = new StringBuilder();
            for (String activityID : map.keySet()) {
                List<NotificationInfo> notifs = map.get(activityID);
                NotificationInfo first = notifs.get(0);

                PluginConfig config = CommonsUtils.getService(PluginSettingService.class).getPluginConfig(templateContext.getPluginId());
                Locale locale = org.exoplatform.commons.notification.NotificationUtils.getLocale(templateContext.getLanguage());
                String resourcePath = config.getBundlePath();

                //. Count user
                List<String> creators = new ArrayList<>();
                for (NotificationInfo n : notifs) {
                    String creator = n.getValueOwnerParameter(NotificationUtils.CREATOR);
                    creators.remove(creator);
                    creators.add(creator);
                }
                Collections.reverse(creators);

                IdentityManager idManager = CommonsUtils.getService(IdentityManager.class);
                Profile lastUser = idManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, creators.get(0), true).getProfile();
                String lastProfileURL = LinkProviderUtils.getRedirectUrl("user", creators.get(0));
                String user = "<a href=\"" + lastProfileURL + "\" style=\"text-decoration: none; color: #2f5e92; font-family: 'HelveticaNeue Bold', Helvetica, Arial, sans-serif\">" + encoder.encode(lastUser.getFullName()) + "</a>";

                if (creators.size() <= 1) {
                    templateContext.digestType(ElementType.DIGEST_ONE.getValue());
                } else {
                    templateContext.digestType(ElementType.DIGEST_MORE.getValue());

                    lastUser = idManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, creators.get(1), true).getProfile();
                    lastProfileURL = LinkProviderUtils.getRedirectUrl("user", creators.get(1));

                    if (creators.size() == 2) {
                        user += " " + TemplateUtils.getResourceBundle("Notification.label.and", locale, resourcePath);
                    } else {
                        user += ", ";
                    }
                    user += " <a href=\"" + lastProfileURL + "\" style=\"text-decoration: none; color: #2f5e92; font-family: 'HelveticaNeue Bold', Helvetica, Arial, sans-serif\">" + encoder.encode(lastUser.getFullName()) + "</a>";

                    if (creators.size() == 3) {
                        user += " " + TemplateUtils.getResourceBundle("Notification.label.one.other", locale, resourcePath);
                    } else if (creators.size() > 3) {
                        String s = TemplateUtils.getResourceBundle("Notification.label.more.other", locale, resourcePath);
                        s = s.replace("{0}", String.valueOf(creators.size() - 2));
                        user += " " + s;
                    }
                }
                templateContext.put("USER", user);


            }

            return sb.toString(); }
    }


    private class RateTemplateBuilder extends TemplateBuilder {
        protected String buildDigestMsg(List<NotificationInfo> notifications, TemplateContext templateContext) {
            EntityEncoder encoder = HTMLEntityEncoder.getInstance();

            Map<String, List<NotificationInfo>> map = new HashMap<String, List<NotificationInfo>>();

            StringBuilder sb = new StringBuilder();
            for (String activityID : map.keySet()) {
                List<NotificationInfo> notifs = map.get(activityID);
                NotificationInfo first = notifs.get(0);

                PluginConfig config = CommonsUtils.getService(PluginSettingService.class).getPluginConfig(templateContext.getPluginId());
                Locale locale = org.exoplatform.commons.notification.NotificationUtils.getLocale(templateContext.getLanguage());
                String resourcePath = config.getBundlePath();

                //. Count user
                List<String> creators = new ArrayList<>();
                for (NotificationInfo n : notifs) {
                    String creator = n.getValueOwnerParameter(NotificationUtils.CREATOR);
                    creators.remove(creator);
                    creators.add(creator);
                }
                Collections.reverse(creators);

                IdentityManager idManager = CommonsUtils.getService(IdentityManager.class);
                Profile lastUser = idManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, creators.get(0), true).getProfile();
                String lastProfileURL = LinkProviderUtils.getRedirectUrl("user", creators.get(0));
                String user = "<a href=\"" + lastProfileURL + "\" style=\"text-decoration: none; color: #2f5e92; font-family: 'HelveticaNeue Bold', Helvetica, Arial, sans-serif\">" + encoder.encode(lastUser.getFullName()) + "</a>";

                if (creators.size() <= 1) {
                    templateContext.digestType(ElementType.DIGEST_ONE.getValue());
                } else {
                    templateContext.digestType(ElementType.DIGEST_MORE.getValue());

                    lastUser = idManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, creators.get(1), true).getProfile();
                    lastProfileURL = LinkProviderUtils.getRedirectUrl("user", creators.get(1));

                    if (creators.size() == 2) {
                        user += " " + TemplateUtils.getResourceBundle("Notification.label.and", locale, resourcePath);
                    } else {
                        user += ", ";
                    }
                    user += " <a href=\"" + lastProfileURL + "\" style=\"text-decoration: none; color: #2f5e92; font-family: 'HelveticaNeue Bold', Helvetica, Arial, sans-serif\">" + encoder.encode(lastUser.getFullName()) + "</a>";

                    if (creators.size() == 3) {
                        user += " " + TemplateUtils.getResourceBundle("Notification.label.one.other", locale, resourcePath);
                    } else if (creators.size() > 3) {
                        String s = TemplateUtils.getResourceBundle("Notification.label.more.other", locale, resourcePath);
                        s = s.replace("{0}", String.valueOf(creators.size() - 2));
                        user += " " + s;
                    }
                }
                templateContext.put("USER", user);


            }

            return sb.toString(); }
    }

    private class LikeTemplateBuilder extends TemplateBuilder {
        protected String buildDigestMsg(List<NotificationInfo> notifications, TemplateContext templateContext) {
            EntityEncoder encoder = HTMLEntityEncoder.getInstance();

            Map<String, List<NotificationInfo>> map = new HashMap<String, List<NotificationInfo>>();

            StringBuilder sb = new StringBuilder();
            for (String activityID : map.keySet()) {
                List<NotificationInfo> notifs = map.get(activityID);
                NotificationInfo first = notifs.get(0);

                PluginConfig config = CommonsUtils.getService(PluginSettingService.class).getPluginConfig(templateContext.getPluginId());
                Locale locale = org.exoplatform.commons.notification.NotificationUtils.getLocale(templateContext.getLanguage());
                String resourcePath = config.getBundlePath();

                //. Count user
                List<String> creators = new ArrayList<>();
                for (NotificationInfo n : notifs) {
                    String creator = n.getValueOwnerParameter(NotificationUtils.CREATOR);
                    creators.remove(creator);
                    creators.add(creator);
                }
                Collections.reverse(creators);

                IdentityManager idManager = CommonsUtils.getService(IdentityManager.class);
                Profile lastUser = idManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, creators.get(0), true).getProfile();
                String lastProfileURL = LinkProviderUtils.getRedirectUrl("user", creators.get(0));
                String user = "<a href=\"" + lastProfileURL + "\" style=\"text-decoration: none; color: #2f5e92; font-family: 'HelveticaNeue Bold', Helvetica, Arial, sans-serif\">" + encoder.encode(lastUser.getFullName()) + "</a>";

                if (creators.size() <= 1) {
                    templateContext.digestType(ElementType.DIGEST_ONE.getValue());
                } else {
                    templateContext.digestType(ElementType.DIGEST_MORE.getValue());

                    lastUser = idManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, creators.get(1), true).getProfile();
                    lastProfileURL = LinkProviderUtils.getRedirectUrl("user", creators.get(1));

                    if (creators.size() == 2) {
                        user += " " + TemplateUtils.getResourceBundle("Notification.label.and", locale, resourcePath);
                    } else {
                        user += ", ";
                    }
                    user += " <a href=\"" + lastProfileURL + "\" style=\"text-decoration: none; color: #2f5e92; font-family: 'HelveticaNeue Bold', Helvetica, Arial, sans-serif\">" + encoder.encode(lastUser.getFullName()) + "</a>";

                    if (creators.size() == 3) {
                        user += " " + TemplateUtils.getResourceBundle("Notification.label.one.other", locale, resourcePath);
                    } else if (creators.size() > 3) {
                        String s = TemplateUtils.getResourceBundle("Notification.label.more.other", locale, resourcePath);
                        s = s.replace("{0}", String.valueOf(creators.size() - 2));
                        user += " " + s;
                    }
                }
                templateContext.put("USER", user);


            }

            return sb.toString(); }
    }

    public static String getExcerpt(String str, int len) {
        if (str == null) {
            return "";
        } else if (str.length() > len) {
            str = str.substring(0, len);
            int lastSpace = str.lastIndexOf(" ");
            return ((lastSpace > 0) ? str.substring(0, lastSpace) : str) + "...";
        } else {
            return str;
        }
    }

}
