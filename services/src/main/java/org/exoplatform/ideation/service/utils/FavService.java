package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.FavoriteImpDAO;
import org.exoplatform.ideation.dto.FavoritDTO;
import org.exoplatform.ideation.dto.OrderIdeaDTO;
import org.exoplatform.ideation.entities.FavoriteEntity;
import org.exoplatform.ideation.service.Mapper.FavoritMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FavService {
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  private static final Log LOG = ExoLogger.getExoLogger(FavoriteEntity.class);
  private FavoritMapper favoritMapper;
  private FavoriteImpDAO favoriteImpDAO;
  protected IdentityManager identityManager = null;


  public FavService(FavoritMapper favoritMapper, FavoriteImpDAO favoriteImpDAO) {
    this.favoritMapper = CommonsUtils.getService(FavoritMapper.class);
    this.favoriteImpDAO = CommonsUtils.getService(FavoriteImpDAO.class);
    identityManager = CommonsUtils.getService(IdentityManager.class);

  }

  public List<FavoritDTO> getAllFavByUser(String user) {
    try {
      List<FavoriteEntity> favoriteEntities = favoriteImpDAO.getFavbyuser(user);
      if (favoriteEntities != null) {
        return favoritMapper.FavsToFavDTOs(favoriteEntities);
      }
    } catch (Exception e) {
      LOG.error("Error to find fav by idea", e.getMessage());

    }
    return null;

  }

  public List<FavoritDTO> getallbyidea(Long id) {
    try {
      List<FavoriteEntity> favs = favoriteImpDAO.getFavByIdea(id);
      if (favs != null) {
        return favoritMapper.FavsToFavDTOs(favs);
      }
    } catch (Exception e) {
      LOG.error("Error to find fav by idea", e.getMessage());

    }
    return null;
  }

  public List<FavoritDTO> getallbyIdeaANDuser(String user, Long id) {
    try {
      List<FavoriteEntity> favs = favoriteImpDAO.getFavByUserAndIdea(user, id);
      if (favs != null) {
        return favoritMapper.FavsToFavDTOs(favs);
      }
    } catch (Exception e) {
      LOG.error("Error to find fav by idea", e.getMessage());

    }
    return null;
  }

  @ExoTransactional
  public FavoritDTO addFav(FavoritDTO favoritDTO) {
    FavoriteEntity favoriteEntity = null;
    try {
      favoriteEntity = favoriteImpDAO.create(favoritMapper.FavdtoTofav(favoritDTO));
    } catch (Exception e) {
      LOG.error("Error to create badge with title {}", favoritDTO.getUser(), e);
    }

    return favoritMapper.FavToFavDTO(favoriteEntity);
  }

  public List<FavoriteEntity> getFavUser() {
    try {
      String user = ConversationState.getCurrent().getIdentity().getUserId();

      List<FavoriteEntity> faventity = favoriteImpDAO.getFavbyuser(user);

      return faventity;
    } catch (Exception e) {
      LOG.error("Error to get Fav by user");
    }
    return null;
  }

  public List<OrderIdeaDTO> getAllOrderByFav() {
    try {
      //--- load all Rules
      List<OrderIdeaDTO> list1 = new ArrayList<>();
      List<FavoriteEntity> favorits = favoriteImpDAO.getAllOrderByFav();
      int i = 0;
      while (i < favorits.size()) {
        OrderIdeaDTO orderideadto = new OrderIdeaDTO();
        orderideadto.setId(favorits.get(i).getIdea().getId());
        orderideadto.setTitle(favorits.get(i).getIdea().getTitle());
        orderideadto.setUser(favorits.get(i).getIdea().getUser());
        orderideadto.setNb(favoriteImpDAO.getnumberOffav(favorits.get(i).getIdea().getId()));
        list1.add(orderideadto);
        i++;

      }
      return list1;


    } catch (Exception e) {
      LOG.error("Error to find Idea Ordered", e.getMessage());
    }

    return null;
  }

  @ExoTransactional
  public void deleteFav(Long id) {
    FavoriteEntity favoriteEntity = null;
    favoriteEntity = favoriteImpDAO.find(id);
    if (favoriteEntity != null) {
      try {
        favoriteImpDAO.delete(favoriteEntity);
      } catch (Exception e) {
        LOG.error("Error to delete rating with id {}", id, e);
      }
    }
  }


}
