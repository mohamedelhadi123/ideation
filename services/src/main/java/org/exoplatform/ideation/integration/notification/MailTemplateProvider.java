/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.ideation.integration.notification;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.annotation.TemplateConfig;
import org.exoplatform.commons.api.notification.annotation.TemplateConfigs;
import org.exoplatform.commons.api.notification.channel.template.AbstractTemplateBuilder;
import org.exoplatform.commons.api.notification.channel.template.TemplateProvider;
import org.exoplatform.commons.api.notification.model.MessageInfo;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.api.notification.service.template.TemplateContext;
import org.exoplatform.commons.notification.template.TemplateUtils;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.commons.utils.HTMLEntityEncoder;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.notification.LinkProviderUtils;
import org.gatein.common.text.EntityEncoder;

@TemplateConfigs(templates = {

    @TemplateConfig(pluginId = AddIdeaPlugin.ID, template = "war:/notification/templates/mail/AddIdeaPlugin.gtmpl"),
    @TemplateConfig(pluginId = UpdateIdeaPlugin.ID, template = "war:/notification/templates/mail/UpdateIdeaPlugin.gtmpl"),
    @TemplateConfig( pluginId = AddCommentPlugin.ID, template="war:/notification/templates/mail/AddCommentPlugin.gtmpl")


})
public class MailTemplateProvider extends TemplateProvider {
  //--- Use a dedicated DateFormatter to handle date pattern coming from underlying levels : Wed Mar 15 01:00:00 CET 2017
  // --- Create formatter
  protected DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
  //protected DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
  protected static Log log = ExoLogger.getLogger(MailTemplateProvider.class);

  public MailTemplateProvider(InitParams initParams) {
    super(initParams);

    this.templateBuilders.put(PluginKey.key(AddIdeaPlugin.ID), new TemplateBuilder());
    this.templateBuilders.put(PluginKey.key(UpdateIdeaPlugin.ID), new TemplateBuilder());
    this.templateBuilders.put(PluginKey.key(AddCommentPlugin.ID), new TemplateBuilder());
    

  }
  
  private class TemplateBuilder extends AbstractTemplateBuilder {
    @Override
    protected MessageInfo makeMessage(NotificationContext ctx) {

      EntityEncoder encoder = HTMLEntityEncoder.getInstance();
      
      NotificationInfo notification = ctx.getNotificationInfo();
      String language = getLanguage(notification);
      log.info("Prepare Mail notif for "+ notification.getKey().getId());
      String ideaCreator = notification.getValueOwnerParameter(NotificationUtils.IDEA_CREATOR);
      String ideaTitle = notification.getValueOwnerParameter(NotificationUtils.IDEA_TITLE);
        String CommentCreator=notification.getValueOwnerParameter(NotificationUtils.COMMENT_CREATOR);
if(ideaCreator==null){
    ideaCreator=notification.getValueOwnerParameter(NotificationUtils.COMMENT_CREATOR);
}


        TemplateContext templateContext = new TemplateContext(notification.getKey().getId(), language);
      IdentityManager identityManager = CommonsUtils.getService(IdentityManager.class);
      Identity author = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, ideaCreator, true);
      Profile profile = author.getProfile();
      //creator
      templateContext.put("USER", encoder.encode(profile.getFullName()));
      templateContext.put("AVATAR", LinkProviderUtils.getUserAvatarUrl(profile));
      templateContext.put("PROFILE_URL", LinkProviderUtils.getRedirectUrl("user", author.getRemoteId()));
      //receiver
      Identity receiver = CommonsUtils.getService(IdentityManager.class).getOrCreateIdentity(OrganizationIdentityProvider.NAME, notification.getTo(), true);
      templateContext.put("FIRST_NAME", encoder.encode(receiver.getProfile().getProperty(Profile.FIRST_NAME).toString()));
      templateContext.put("LAST_NAME", encoder.encode(receiver.getProfile().getProperty(Profile.LAST_NAME).toString()));
      templateContext.put("IDEA_TITLE", encoder.encode(ideaTitle));
      templateContext.put("IDEA_CREATOR",ideaCreator);
        templateContext.put("COMMENT_CREATOR",ideaCreator);




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
      return sb.toString();
    }
  }

}
