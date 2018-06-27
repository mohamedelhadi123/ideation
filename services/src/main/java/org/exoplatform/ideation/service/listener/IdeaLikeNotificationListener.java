package org.exoplatform.ideation.service.listener;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.notification.impl.NotificationContextImpl;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.entities.dto.LikeDTO;
import org.exoplatform.ideation.integration.IdeaLikedPlugin;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import java.util.HashSet;
import java.util.Set;

public class IdeaLikeNotificationListener extends Listener<Set<String>,IdeaDTO> {
    private static final Log LOG = ExoLogger.getLogger(IdeaLikeNotificationListener.class);
    @Override
    public void onEvent(Event event) throws Exception {
        LikeDTO like=(LikeDTO)event.getData();
        Set<String> receivers = new HashSet<String>();
        IdeaDTO vr = CommonsUtils.getService(IdeaService.class).getIdea(like.getIdeaId());
        receivers.add(vr.getCreatedBy());
        NotificationContext ctx = NotificationContextImpl.cloneInstance().append(IdeaLikedPlugin.LIKE, like).append(IdeaLikedPlugin.RECEIVERS, receivers).append(IdeaLikedPlugin.IDEA, vr);
        ctx.getNotificationExecutor().with(ctx.makeCommand(PluginKey.key(IdeaLikedPlugin.ID))).execute(ctx);
    }
}
