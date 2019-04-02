package org.exoplatform.addons.ideation.portlets.ideationAdministration;
import juzu.impl.request.ControllerHandler;
import juzu.impl.request.ControlParameter;
import juzu.impl.request.PhaseParameter;
import juzu.impl.request.ContextualParameter;
import juzu.impl.request.BeanParameter;
import juzu.impl.common.Tools;
import java.util.Arrays;
import juzu.request.Phase;
import juzu.impl.plugin.controller.descriptor.ControllerDescriptor;
import javax.annotation.Generated;
import juzu.impl.common.Cardinality;
import juzu.impl.request.Request;
@Generated(value={})
public class IdeationAdministrationController_ {
private static final Class<org.exoplatform.addons.ideation.portlets.ideationAdministration.IdeationAdministrationController> TYPE = org.exoplatform.addons.ideation.portlets.ideationAdministration.IdeationAdministrationController.class;
private static final ControllerHandler<Phase.Resource> method_0 = new ControllerHandler<Phase.Resource>("IdeationAdministrationController.getData",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getData"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getData() { return Request.createResourceDispatch(method_0); }
private static final ControllerHandler<Phase.Resource> method_1 = new ControllerHandler<Phase.Resource>("IdeationAdministrationController.getBundle",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getBundle"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getBundle() { return Request.createResourceDispatch(method_1); }
private static final ControllerHandler<Phase.View> method_2 = new ControllerHandler<Phase.View>("IdeationAdministrationController.index",Phase.VIEW,TYPE,Tools.safeGetMethod(TYPE,"index"), Arrays.<ControlParameter>asList());
public static Phase.View.Dispatch index() { return Request.createViewDispatch(method_2); }
private static final ControllerHandler<Phase.Resource> method_3 = new ControllerHandler<Phase.Resource>("IdeationAdministrationController.updateIdea",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"updateIdea",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch updateIdea() { return Request.createResourceDispatch(method_3); }
private static final ControllerHandler<Phase.Resource> method_4 = new ControllerHandler<Phase.Resource>("IdeationAdministrationController.deleteIdea",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"deleteIdea",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch deleteIdea() { return Request.createResourceDispatch(method_4); }
private static final ControllerHandler<Phase.Resource> method_5 = new ControllerHandler<Phase.Resource>("IdeationAdministrationController.getIdeas",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getIdeas"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getIdeas() { return Request.createResourceDispatch(method_5); }
public static final ControllerDescriptor DESCRIPTOR = new ControllerDescriptor(TYPE,Arrays.<ControllerHandler<?>>asList(method_0,method_1,method_2,method_3,method_4,method_5));
}
