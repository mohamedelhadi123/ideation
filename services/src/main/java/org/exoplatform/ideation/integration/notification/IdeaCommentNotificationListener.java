


        package org.exoplatform.ideation.storage.listener;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.notification.impl.NotificationContextImpl;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.entities.dto.CommentDTO;
import org.exoplatform.ideation.entities.dto.IdeaDTO;

import java.util.HashSet;
import java.util.Set;

import org.exoplatform.ideation.integration.notification.IdeaCommentPlugin;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


public class IdeaCommentNotificationListener extends Listener<Set<String>,IdeaDTO> {
    private static final Log LOG = ExoLogger.getLogger(IdeaCommentNotificationListener.class);


    @Override
    public void onEvent(Event event) throws Exception {
        CommentDTO comment=(CommentDTO)event.getData();

        Set<String> receivers = new HashSet<String>();
        IdeaDTO vr = CommonsUtils.getService(IdeaService.class).getIdea(comment.getIdeaId());

        receivers.add(vr.getCreatedBy());




        NotificationContext ctx = NotificationContextImpl.cloneInstance().append(IdeaCommentPlugin.COMMENT, comment).append(IdeaCommentPlugin.RECEIVERS, receivers).append(IdeaCommentPlugin.IDEA, vr);
        ctx.getNotificationExecutor().with(ctx.makeCommand(PluginKey.key(IdeaCommentPlugin.ID))).execute(ctx);
    }
}
