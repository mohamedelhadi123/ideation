package org.exoplatform.addons.ideation.portlets.ideationAdministration;

import juzu.*;
import juzu.impl.common.JSON;
import juzu.plugin.jackson.Jackson;
import juzu.template.Template;
import org.exoplatform.commons.juzu.ajax.Ajax;
import org.exoplatform.commons.utils.PropertyManager;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;
import javax.inject.Inject;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;



public class IdeationAdministrationController {
    @SessionScoped
    private static Log log = ExoLogger.getLogger(org.exoplatform.addons.ideation.portlets.ideaFront.IdeaFrontController.class);

    ResourceBundle bundle;

    @Inject
    @Path("index.gtmpl")
    Template indexTmpl;


    @Inject
    IdentityManager identityManager;

    @Inject
    RepositoryService repositoryService;


    @Inject
    IdeaService ideaService;


    private String bundleString;

    private final String currentUser = ConversationState.getCurrent().getIdentity().getUserId();



    @View
    public Response.Content index() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("currentUser",currentUser);
        return indexTmpl.with(parameters).ok();
    }


    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<IdeaDTO> getIdeas() {
        try {
            return ideaService.getAllIdeas();
        } catch (Throwable e) {
            return null;
        }
    }



    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public Response deleteIdea(@Jackson IdeaDTO obj) throws Exception {
        try {
            ideaService.delete(obj);
            return Response.ok();
        } catch (Exception e) {
            log.error("Error when updating Idea", e);
            return Response.error("");
        }
    }


    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void updateIdea(@Jackson IdeaDTO obj) {
        obj = ideaService.save(obj,false);
    }


    @Ajax
    @Resource
    @MimeType.JSON
    @Jackson
    public Response getData() {
        try {
            JSON data = new JSON();
            return Response.ok(data.toString());
        } catch (Throwable e) {
            log.error("error while getting context", e);
            return Response.status(500);
        }
    }

    @Ajax
    @Resource
    @MimeType.JSON
    @Jackson
    public Response getBundle() {
        try {
            if (!PropertyManager.isDevelopping() && bundleString != null && getResourceBundle().getLocale().equals(PortalRequestContext.getCurrentInstance().getLocale())) {
                return Response.ok(bundleString);
            }
            bundle = getResourceBundle(PortalRequestContext.getCurrentInstance().getLocale());
            JSON data = new JSON();
            Enumeration<String> enumeration = getResourceBundle().getKeys();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                try {
                    data.set(key.replaceAll("(.*)\\.", ""), getResourceBundle().getObject(key));
                } catch (MissingResourceException e) {
                    // Nothing to do, this happens sometimes
                }
            }
            data.set("currentUser",currentUser);
            bundleString = data.toString();
            return Response.ok(bundleString);
        } catch (Throwable e) {
            log.error("error while getting ideas", e);
            return Response.status(500);
        }
    }

    private ResourceBundle getResourceBundle(Locale locale) {
        return bundle = ResourceBundle.getBundle("locale.portlet.idea-addon", locale, this.getClass().getClassLoader());
    }

    private ResourceBundle getResourceBundle() {
        if (bundle == null) {
            bundle = getResourceBundle(PortalRequestContext.getCurrentInstance().getLocale());
        }
        return bundle;
    }


}


