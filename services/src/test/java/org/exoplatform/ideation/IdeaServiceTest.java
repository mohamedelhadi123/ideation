/*package org.exoplatform.ideation;

import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.exoplatform.ideation.dao.IdeaDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.listener.ListenerService;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.exoplatform.ideation.dao.IdeaDAO;
import org.exoplatform.ideation.service.*;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.slf4j.impl.StaticLoggerBinder;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class IdeaServiceTest {

  IdeaService IdeaService;

  ListenerService listenerService;

  @Mock
  ExoContainer container;
  @Mock
  IdeaDAO daoHandler;


  //ArgumentCaptors are how you can retrieve objects that were passed into a method call
  @Captor
  ArgumentCaptor<IdeaDAO> IdeaCaptor;

  @Before
  public void setUp() throws Exception {
    // Make sure the container is started to prevent the ExoTransactional annotation to fail
    PortalContainer.getInstance();

    listenerService = new ListenerService(new ExoContainerContext(container));



    Identity root = new Identity("root");
    ConversationState.setCurrent(new ConversationState(root));
  }

  @After
  public void tearDown() {
    IdeaService = null;
    ConversationState.setCurrent(null);
  }


}
*/