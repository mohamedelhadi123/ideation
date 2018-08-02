package org.exoplatform.ideation.service.listener;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.notification.impl.NotificationContextImpl;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.entities.dto.RateDTO;
import org.exoplatform.ideation.integration.notification.IdeaRatedPlugin;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.HashSet;
import java.util.Set;


    public class IdeaRateNotificationListener extends Listener<Set<String>,IdeaDTO> {
        private static final Log LOG = ExoLogger.getLogger(org.exoplatform.ideation.service.listener.IdeaRateNotificationListener.class);
        @Override
        public void onEvent(Event event) throws Exception {
            RateDTO rate=(RateDTO)event.getData();
            Set<String> receivers = new HashSet<String>();
            IdeaDTO vr = CommonsUtils.getService(IdeaService.class).getIdea(rate.getIdeaId());
            receivers.add(vr.getCreatedBy());
            NotificationContext ctx = NotificationContextImpl.cloneInstance().append(IdeaRatedPlugin.RATE, rate).append(IdeaRatedPlugin.RECEIVERS, receivers).append(IdeaRatedPlugin.IDEA, vr);
            ctx.getNotificationExecutor().with(ctx.makeCommand(PluginKey.key(IdeaRatedPlugin.ID))).execute(ctx);

        }
    }


