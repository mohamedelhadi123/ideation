package org.exoplatform.ideation.integration.notification;
import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.BaseNotificationPlugin;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.container.component.RequestLifeCycle;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.web.WebAppController;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class AbstractNotificationPlugin extends BaseNotificationPlugin {

    public AbstractNotificationPlugin(InitParams initParams) {
        super(initParams);
    }

    @Override
    public NotificationInfo makeNotification(NotificationContext ctx) {
        IdeaEntity ideaEntity = ctx.value(NotificationUtils.IDEA);
        ExoContainer container = getContainer();
        WebAppController controller = container.getComponentInstanceOfType(WebAppController.class);
        RequestLifeCycle.begin(container);
        Set<String> receivers = getReceiver(ideaEntity, ctx);
        RequestLifeCycle.end();

        return NotificationInfo.instance()
                .to(new LinkedList<String>(receivers))
                .with(NotificationUtils.IDEA_TITLE, ideaEntity.getTitle())
                .with(NotificationUtils.IDEA_DESCRIPTION, ideaEntity.getDescription())
                .key(getKey()).end();
    }

    private ExoContainer getContainer() {
        String containerName = PortalContainer.getCurrentPortalContainerName();
        ExoContainer container = RootContainer.getInstance().getPortalContainer(containerName);
        return container;
    }



    protected Set<String> getReceiver(IdeaEntity ideaEntity, NotificationContext ctx) {
        Set<String> receivers = new HashSet<String>();
        if (ideaEntity.getCreatedBy() != null && !ideaEntity.getCreatedBy().isEmpty()) {
            receivers.add(ideaEntity.getCreatedBy());
        }


        return receivers;
    }


}
