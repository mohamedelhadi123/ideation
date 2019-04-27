package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.FavoriteImpDAO;
import org.exoplatform.ideation.dto.FavoritDTO;
import org.exoplatform.ideation.entities.FavoriteEntity;
import org.exoplatform.ideation.service.Mapper.FavoritMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

public class FavService {
    private static final Log LOG = ExoLogger.getExoLogger(FavoriteEntity.class);
    private FavoritMapper favoritMapper;
    private FavoriteImpDAO favoriteImpDAO;

    public FavService(FavoritMapper favoritMapper, FavoriteImpDAO favoriteImpDAO) {
        this.favoritMapper = CommonsUtils.getService(FavoritMapper.class);
        this.favoriteImpDAO = CommonsUtils.getService(FavoriteImpDAO.class);
    }

    public List<FavoritDTO> getAllFavByUser(String user){
        try{
            List<FavoriteEntity> favoriteEntities=favoriteImpDAO.getFav(user);
            if(favoriteEntities!=null){
                return  favoritMapper.FavsToFavDTOs(favoriteEntities);
            }
        }catch (Exception e){
            LOG.error("Error to find fav by idea", e.getMessage());

        }
        return null;

    }
    public List<FavoritDTO> getallbyid(Long id){
        try {
            List<FavoriteEntity> favs=favoriteImpDAO.getFavById(id);
            if (favs!=null){
                return  favoritMapper.FavsToFavDTOs(favs);
            }
        }catch (Exception e){
            LOG.error("Error to find fav by idea", e.getMessage());

        }
        return null;
    }
    public List<FavoritDTO> getallbyidANDuser(String user,Long id){
        try {
            List<FavoriteEntity> favs=favoriteImpDAO.getFavByUserAndId(user,id);
            if (favs!=null){
                return  favoritMapper.FavsToFavDTOs(favs);
            }
        }catch (Exception e){
            LOG.error("Error to find fav by idea", e.getMessage());

        }
        return null;
    }
    @ExoTransactional
    public FavoritDTO addFav(FavoritDTO favoritDTO){
        FavoriteEntity favoriteEntity=null;
        try{
            favoriteEntity=favoriteImpDAO.create(favoritMapper.FavdtoTofav(favoritDTO));
        } catch (Exception e) {
            LOG.error("Error to create badge with title {}", favoritDTO.getUser() , e);
        }

        return favoritMapper.FavToFavDTO(favoriteEntity);
    }





}
