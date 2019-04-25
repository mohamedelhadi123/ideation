package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.ideation.dto.FavoritDTO;
import org.exoplatform.ideation.entities.FavoriteEntity;
import org.exoplatform.ideation.entities.IdeaEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FavoritMapper {
    public FavoritMapper() {
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
                FavoriteEntity favoriteEntity=new FavoriteEntity();
                favoriteEntity.setUser(favoritDTO.getUser());
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
