package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.ThemeDTO;
import org.exoplatform.ideation.entities.ThemeEntity;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ThemeMapper {
    protected IdentityManager identityManager = null;

    public ThemeMapper() {
        identityManager = CommonsUtils.getService(IdentityManager.class);
    }
    public ThemeDTO ThemeToThemeDTO(ThemeEntity themeEntity){
        return new ThemeDTO(themeEntity);
    }

    public List<ThemeDTO> ThemesToThemesDTO(List<ThemeEntity> themes){
        return themes.stream()
                .filter(Objects::nonNull)
                .map(this::ThemeToThemeDTO)
                .collect(Collectors.toList());
    }

    public ThemeEntity ThemedtoToTheme(ThemeDTO themeDTO){
        try {
            if (themeDTO==null){
                return null;
            }else {
                String user = ConversationState.getCurrent().getIdentity().getUserId();
                ThemeEntity themeEntity=new ThemeEntity();
                themeEntity.setSubject(themeDTO.getSubject());
                themeEntity.setUser(user);

                return themeEntity;
            }
        }catch (Exception pe){
            pe.printStackTrace();
        }
        return null;
    }


}
