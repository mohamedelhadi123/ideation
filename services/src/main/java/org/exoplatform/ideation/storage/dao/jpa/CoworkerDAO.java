package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.domain.CoworkerEntity;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoworkerDAO extends GenericDAOJPAImpl<CoworkerEntity, Long> {
    private  final Logger LOG = LoggerFactory.getLogger(CoworkerDAO.class);

    public List<CoworkerEntity> getCoworker(long ideaId) {
        TypedQuery<CoworkerEntity> query = getEntityManager().createNamedQuery("Coworker.getCoworker", CoworkerEntity.class).setParameter("ideaId", ideaId);
     return   query.getResultList();
    }

}
