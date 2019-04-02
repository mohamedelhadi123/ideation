package org.exoplatform.addons.ideation.portlets.ideaFront;
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
public class IdeaFrontController_ {
private static final Class<org.exoplatform.addons.ideation.portlets.ideaFront.IdeaFrontController> TYPE = org.exoplatform.addons.ideation.portlets.ideaFront.IdeaFrontController.class;
private static final ControllerHandler<Phase.Resource> method_0 = new ControllerHandler<Phase.Resource>("IdeaFrontController.saveLIke",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"saveLIke",org.exoplatform.ideation.entities.dto.LikeDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.LikeDTO.class)));
public static Phase.Resource.Dispatch saveLIke() { return Request.createResourceDispatch(method_0); }
private static final ControllerHandler<Phase.Resource> method_1 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getFavorite",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getFavorite",org.exoplatform.ideation.entities.dto.FavoriteDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("favoriteDTO",org.exoplatform.ideation.entities.dto.FavoriteDTO.class)));
public static Phase.Resource.Dispatch getFavorite() { return Request.createResourceDispatch(method_1); }
private static final ControllerHandler<Phase.Resource> method_2 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getLikes",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getLikes",org.exoplatform.ideation.entities.dto.LikeDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.LikeDTO.class)));
public static Phase.Resource.Dispatch getLikes() { return Request.createResourceDispatch(method_2); }
private static final ControllerHandler<Phase.Resource> method_3 = new ControllerHandler<Phase.Resource>("IdeaFrontController.deleteFavorite",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"deleteFavorite",org.exoplatform.ideation.entities.dto.FavoriteDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.FavoriteDTO.class)));
public static Phase.Resource.Dispatch deleteFavorite() { return Request.createResourceDispatch(method_3); }
private static final ControllerHandler<Phase.Resource> method_4 = new ControllerHandler<Phase.Resource>("IdeaFrontController.deleteLike",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"deleteLike",org.exoplatform.ideation.entities.dto.LikeDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.LikeDTO.class)));
public static Phase.Resource.Dispatch deleteLike() { return Request.createResourceDispatch(method_4); }
private static final ControllerHandler<Phase.Resource> method_5 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getIdea",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getIdea",java.lang.Long.class), Arrays.<ControlParameter>asList(new PhaseParameter("id",java.lang.Long.class,java.lang.Long.class,Cardinality.SINGLE,null)));
public static Phase.Resource.Dispatch getIdea(java.lang.Long id) { return Request.createResourceDispatch(method_5,(Object)id); }
private static final ControllerHandler<Phase.Resource> method_6 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getAttachements",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getAttachements",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch getAttachements() { return Request.createResourceDispatch(method_6); }
private static final ControllerHandler<Phase.Resource> method_7 = new ControllerHandler<Phase.Resource>("IdeaFrontController.saveRate",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"saveRate",org.exoplatform.ideation.entities.dto.RateDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.RateDTO.class)));
public static Phase.Resource.Dispatch saveRate() { return Request.createResourceDispatch(method_7); }
private static final ControllerHandler<Phase.Resource> method_8 = new ControllerHandler<Phase.Resource>("IdeaFrontController.saveIdea",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"saveIdea",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch saveIdea() { return Request.createResourceDispatch(method_8); }
private static final ControllerHandler<Phase.Resource> method_9 = new ControllerHandler<Phase.Resource>("IdeaFrontController.updateIdea",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"updateIdea",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch updateIdea() { return Request.createResourceDispatch(method_9); }
private static final ControllerHandler<Phase.Resource> method_10 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getResults",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getResults",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch getResults() { return Request.createResourceDispatch(method_10); }
private static final ControllerHandler<Phase.Resource> method_11 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getData",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getData"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getData() { return Request.createResourceDispatch(method_11); }
private static final ControllerHandler<Phase.Resource> method_12 = new ControllerHandler<Phase.Resource>("IdeaFrontController.SaveDraft",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"SaveDraft",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch SaveDraft() { return Request.createResourceDispatch(method_12); }
private static final ControllerHandler<Phase.Resource> method_13 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getComments",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getComments"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getComments() { return Request.createResourceDispatch(method_13); }
private static final ControllerHandler<Phase.Resource> method_14 = new ControllerHandler<Phase.Resource>("IdeaFrontController.updateRate",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"updateRate",org.exoplatform.ideation.entities.dto.RateDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.RateDTO.class)));
public static Phase.Resource.Dispatch updateRate() { return Request.createResourceDispatch(method_14); }
private static final ControllerHandler<Phase.Resource> method_15 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getDraftIdeas",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getDraftIdeas",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch getDraftIdeas() { return Request.createResourceDispatch(method_15); }
private static final ControllerHandler<Phase.Resource> method_16 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getBundle",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getBundle"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getBundle() { return Request.createResourceDispatch(method_16); }
private static final ControllerHandler<Phase.Resource> method_17 = new ControllerHandler<Phase.Resource>("IdeaFrontController.SaveFavorite",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"SaveFavorite",org.exoplatform.ideation.entities.dto.FavoriteDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.FavoriteDTO.class)));
public static Phase.Resource.Dispatch SaveFavorite() { return Request.createResourceDispatch(method_17); }
private static final ControllerHandler<Phase.Resource> method_18 = new ControllerHandler<Phase.Resource>("IdeaFrontController.uploadFile",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"uploadFile",java.lang.Long.class,org.apache.commons.fileupload.FileItem.class), Arrays.<ControlParameter>asList(new PhaseParameter("requestId",java.lang.Long.class,java.lang.Long.class,Cardinality.SINGLE,null),new ContextualParameter("file",org.apache.commons.fileupload.FileItem.class)));
public static Phase.Resource.Dispatch uploadFile(java.lang.Long requestId) { return Request.createResourceDispatch(method_18,(Object)requestId); }
private static final ControllerHandler<Phase.Resource> method_19 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getIdeas",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getIdeas"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getIdeas() { return Request.createResourceDispatch(method_19); }
private static final ControllerHandler<Phase.Resource> method_20 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getCoworkersIdea",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getCoworkersIdea",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch getCoworkersIdea() { return Request.createResourceDispatch(method_20); }
private static final ControllerHandler<Phase.Resource> method_21 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getContext",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getContext"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getContext() { return Request.createResourceDispatch(method_21); }
private static final ControllerHandler<Phase.Resource> method_22 = new ControllerHandler<Phase.Resource>("IdeaFrontController.deleteIdea",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"deleteIdea",org.exoplatform.ideation.entities.dto.IdeaDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.IdeaDTO.class)));
public static Phase.Resource.Dispatch deleteIdea() { return Request.createResourceDispatch(method_22); }
private static final ControllerHandler<Phase.Resource> method_23 = new ControllerHandler<Phase.Resource>("IdeaFrontController.saveComment",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"saveComment",org.exoplatform.ideation.entities.dto.CommentDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.CommentDTO.class)));
public static Phase.Resource.Dispatch saveComment() { return Request.createResourceDispatch(method_23); }
private static final ControllerHandler<Phase.Resource> method_24 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getRates",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getRates",org.exoplatform.ideation.entities.dto.RateDTO.class), Arrays.<ControlParameter>asList(new ContextualParameter("obj",org.exoplatform.ideation.entities.dto.RateDTO.class)));
public static Phase.Resource.Dispatch getRates() { return Request.createResourceDispatch(method_24); }
private static final ControllerHandler<Phase.Resource> method_25 = new ControllerHandler<Phase.Resource>("IdeaFrontController.getDraftedIdeasOfCurrentUser",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"getDraftedIdeasOfCurrentUser"), Arrays.<ControlParameter>asList());
public static Phase.Resource.Dispatch getDraftedIdeasOfCurrentUser() { return Request.createResourceDispatch(method_25); }
private static final ControllerHandler<Phase.View> method_26 = new ControllerHandler<Phase.View>("IdeaFrontController.index",Phase.VIEW,TYPE,Tools.safeGetMethod(TYPE,"index"), Arrays.<ControlParameter>asList());
public static Phase.View.Dispatch index() { return Request.createViewDispatch(method_26); }
public static final ControllerDescriptor DESCRIPTOR = new ControllerDescriptor(TYPE,Arrays.<ControllerHandler<?>>asList(method_0,method_1,method_2,method_3,method_4,method_5,method_6,method_7,method_8,method_9,method_10,method_11,method_12,method_13,method_14,method_15,method_16,method_17,method_18,method_19,method_20,method_21,method_22,method_23,method_24,method_25,method_26));
}
