package org.exoplatform.ideation.integration.notification;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.BaseNotificationPlugin;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.entities.dto.RateDTO;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.LinkedList;
import java.util.Set;


public class IdeaRatedPlugin extends BaseNotificationPlugin {
    public final static ArgumentLiteral<RateDTO> RATE = new ArgumentLiteral<RateDTO>(RateDTO.class, "rate");
    public static final ArgumentLiteral<Set> RECEIVERS = new ArgumentLiteral<Set>(Set.class, "receivers");
    public final static ArgumentLiteral<IdeaDTO> IDEA = new ArgumentLiteral<IdeaDTO>(IdeaDTO.class, "idea");


    private static final Log LOG = ExoLogger.getLogger(IdeaRatedPlugin.class);

    public final static String ID = "IdeaRatedPlugin";

    IdentityManager identityManager;

    public IdeaRatedPlugin(InitParams initParams, IdentityManager identityManager) {
        super(initParams);
        this.identityManager = identityManager;

    }
    @Override
    public String getId() {
        return ID;
    }
    @Override
    public boolean isValid(NotificationContext ctx) {
        return true;
    }
    @Override
    protected NotificationInfo makeNotification(NotificationContext ctx) {

        RateDTO obj = ctx.value(RATE);
        Set<String> receivers = ctx.value(RECEIVERS);
        IdeaDTO idea  = ctx.value(IDEA);
        String userId=obj.getAuthor();
        StringBuilder activityId = new StringBuilder(userId);
        activityId.append("-").append(obj.getIdeaId());
        String IdeaUrl = CommonsUtils.getCurrentDomain()+"/portal/intranet/Manage%20Ideas?rid="+obj.getIdeaId();

        return NotificationInfo.instance()

                .setFrom(userId)
                .to(new LinkedList<String>(receivers))
                .with(NotificationUtils.CREATOR, userId)
                // .with(NotificationUtils.USER_NAME, idea.getCreatedBy())
                .with(NotificationUtils.USER_NAME, idea.getCreatedBy())
                .with(NotificationUtils.IDEAURL, IdeaUrl)
                .with(NotificationUtils.ACTIVITY_ID, activityId.toString())
                .key(getKey()).end();

    }
}