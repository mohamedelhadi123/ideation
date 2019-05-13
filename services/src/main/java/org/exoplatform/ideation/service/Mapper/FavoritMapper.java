package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.FavoritDTO;
import org.exoplatform.ideation.entities.FavoriteEntity;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FavoritMapper {
    protected IdentityManager identityManager = null;

    public FavoritMapper() {
        identityManager = CommonsUtils.getService(IdentityManager.class);

    }

    public FavoritDTO FavToFavDTO(FavoriteEntity favoriteEntity){return new FavoritDTO(favoriteEntity);}

    public List<FavoritDTO> FavsToFavDTOs(List<FavoriteEntity> favs){
        return favs.stream()
                .filter(Objects::nonNull)
                .map(this::FavToFavDTO)
                .collect(Collectors.toList());
    }
    public FavoriteEntity FavdtoTofav(FavoritDTO favoritDTO){
        try{
            if(favoritDTO== null){
                return null;
            }else {
                String user = ConversationState.getCurrent().getIdentity().getUserId();

                FavoriteEntity favoriteEntity=new FavoriteEntity();
                favoriteEntity.setUser(user);
                IdeaEntity idea=this.IdeaFormLongId(favoritDTO.getId_Ideaf());
                favoriteEntity.setIdea(idea);
                return favoriteEntity;

            }
        }catch (Exception pe) {
            pe.printStackTrace();
        }
        return null;
    }



    public IdeaEntity IdeaFormLongId(Long id){
        IdeaEntity ideaEntity=new IdeaEntity();
        ideaEntity.setId(id);
        return ideaEntity;
    }
}
