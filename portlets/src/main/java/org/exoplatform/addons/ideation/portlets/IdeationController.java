package org.exoplatform.addons.ideation.portlets;
import juzu.Path;
import juzu.Response;
import juzu.SessionScoped;
import juzu.View;
import juzu.template.Template;
import javax.inject.Inject;
import javax.portlet.PortletPreferences;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.web.application.RequestContext;

import java.io.IOException;

@SessionScoped
public class IdeationController {
    @Inject
    @Path("index.gtmpl")
    Template indexT;

    @Inject
    PortletPreferences portletPreferences;
    OrganizationService organizationService_;
    @Inject
    IdeaService ideaService;
    @View
    public Response.Content index() {
        //--- Get context pref each time we refresh the page
        String remoteUser = RequestContext.getCurrentInstance().getRemoteUser();

        return indexT.with()
                .set("country", "country")
                .set("code", "code")
                .ok();
    }


}
