package org.exoplatform.ideation.integration.notification;

import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.ideation.entities.domain.CommentEntity;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.domain.LikeEntity;

import java.util.Set;

public class NotificationUtils {
    public final static ArgumentLiteral<IdeaEntity> IDEA = new ArgumentLiteral<IdeaEntity>(IdeaEntity.class, "idea");
    public final static ArgumentLiteral<CommentEntity> COMMENT = new ArgumentLiteral<CommentEntity>(CommentEntity.class, "comment");
    public final static ArgumentLiteral<LikeEntity> LIKE = new ArgumentLiteral<LikeEntity>(LikeEntity.class, "like");

    public final static String CREATOR = "creator";
    public static final ArgumentLiteral<Set> RECEIVERS = new ArgumentLiteral<Set>(Set.class, "receivers");
    public static final String IDEAS = "ideas";
    public final static String IDEA_TITLE = "ideaName";
    public final static String IDEA_DESCRIPTION = "ideaDescription";
    public final static String ACTIVITY_ID = "activityId";
    public final static String USER_NAME = "userName";
    public final static  String IDEAURL = "IdeaUrl";
    public final static String COMMENT_TEXT = "commentText";


}