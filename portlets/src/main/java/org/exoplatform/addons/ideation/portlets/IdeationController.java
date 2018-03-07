package org.exoplatform.addons.ideation.portlets;

import juzu.Path;
import juzu.Response;
import juzu.View;
import juzu.template.Template;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.web.application.RequestContext;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;

public class IdeationController {
    @Inject
    @Path("index.gtmpl")
    Template indexT;

    @Inject
    PortletPreferences portletPreferences;

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
