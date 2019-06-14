package org.exoplatform.ideation.integration.notification;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.BaseNotificationPlugin;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.HashSet;
import java.util.Set;

public class UpdateIdeaPlugin  extends BaseNotificationPlugin {
    private static final Log LOG = ExoLogger.getLogger(UpdateIdeaPlugin.class);
    public final static String ID = "UpdateIdeaPlugin";
    public final static ArgumentLiteral<IdeaDTO> IDEA = new ArgumentLiteral<IdeaDTO>(IdeaDTO.class, "ticket");
    IdentityManager identityManager;

    public UpdateIdeaPlugin(InitParams initParams,IdentityManager identityManager) {
        super(initParams);
        this.identityManager = identityManager;
    }

    public UpdateIdeaPlugin(InitParams initParams) {
        super(initParams);
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public boolean isValid(NotificationContext notificationContext) {
        return true;
    }

    @Override
    protected NotificationInfo makeNotification( NotificationContext ctx) {
        IdeaDTO idea = ctx.value(IDEA);
        Set<String> receivers = new HashSet<String>();
        receivers.add(idea.getUser());

        return NotificationInfo.instance()

                .setFrom(idea.getUser())
                .with(NotificationUtils.IDEA_TITLE, idea.getTitle())
                .with(NotificationUtils.IDEA_CREATOR, idea.getUser())
                .setSendAll(true)
                .key(getKey()).end();
    }
}
