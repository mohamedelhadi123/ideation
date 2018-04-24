package org.exoplatform.ideation.storage.listener;

import org.exoplatform.ideation.entities.domain.IdeaEntity;

import javax.persistence.*;

public class AuditingEntityListener {

    public AuditingEntityListener() {

    }

    @PostPersist
    public void methodInvokedAfterPersist(IdeaEntity IdeaEntity) {

    }
    @PostUpdate
    public void methodInvokedAfterUpdate(IdeaEntity IdeaEntity) {

    }

    @PostRemove
    public void methodInvokedAfterRemove(IdeaEntity IdeaEntity) {

    }
}
