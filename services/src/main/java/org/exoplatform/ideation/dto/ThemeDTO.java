package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.ThemeEntity;
/*
 *
 *
 * Class DTO For ThemeEntity
 *
 *
 */
public class ThemeDTO {
    private Long id;
    private  String user;
    private String subject;

    public ThemeDTO(ThemeEntity themeEntity) {
        this.id=themeEntity.getId();
        this.user = themeEntity.getUser();
        this.subject = themeEntity.getSubject();
    }

    public ThemeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
