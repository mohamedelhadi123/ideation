package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.CommentEntity;


import javax.persistence.TypedQuery;
import java.util.List;

public class CommentImpDAO extends GenericDAOJPAImpl<CommentEntity,Long> {

    public List<CommentEntity> getComment(Long id){
        TypedQuery<CommentEntity> query = getEntityManager().createNamedQuery("Comment.getComment", CommentEntity.class);
        query.setParameter("id",id);
        return query.getResultList();



    }
}
