package org.exoplatform.ideation.integration.notification;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.BaseNotificationPlugin;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.ideation.dto.CommentDTO;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.manager.IdentityManager;
import java.util.HashSet;
import java.util.Set;

public class AddCommentPlugin extends BaseNotificationPlugin {


 public static final Log LOG= ExoLogger.getExoLogger(AddCommentPlugin.class);
 public final static String ID ="AddCommentPlugin";

public final static ArgumentLiteral<CommentDTO> COMMENT =new ArgumentLiteral<CommentDTO>(CommentDTO.class,"ticket");






    IdentityManager identityManager;

    public AddCommentPlugin(InitParams initParams, IdentityManager identityManager) {
        super(initParams);
        this.identityManager = identityManager;
    }

    public AddCommentPlugin(InitParams initParams) {
        super(initParams);
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

        CommentDTO comment =ctx.value(COMMENT);
        Set<String> receivers =new HashSet<String>();
        receivers.add(comment.getUser());
        return NotificationInfo.instance()
                .setFrom(comment.getUser())
                .with(NotificationUtils.COMMENT_CREATOR, comment.getUser())
                .setSendAll(true)
                .key(getKey()).end();


    }
}
