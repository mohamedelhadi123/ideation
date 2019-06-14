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


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.BaseNotificationPlugin;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.manager.IdentityManager;


public class AddIdeaPlugin extends BaseNotificationPlugin {

  private static final Log LOG = ExoLogger.getLogger(AddIdeaPlugin.class);

  public final static String ID = "AddIdeaPlugin";

  public final static ArgumentLiteral<IdeaDTO> IDEA = new ArgumentLiteral<IdeaDTO>(IdeaDTO.class, "ticket");


  IdentityManager identityManager;

  public AddIdeaPlugin(InitParams initParams, IdentityManager identityManager) {

    super(initParams);
    this.identityManager = identityManager;

  }

  public AddIdeaPlugin(InitParams initParams) {
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