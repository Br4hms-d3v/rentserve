package be.brahms.TFE_RentServe.services;

import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorDTO;
import java.util.List;

/**
 * Service interface for managing user favour. Defines business operations related to userFavor
 * entity
 */
public interface UserFavorService {

  /**
   * This method get a list of all users favours
   *
   * @return list of users favour
   */
  List<UserFavorDTO> findAllUserFavour();

  /**
   * This method get a list of user favour by Favor ID
   *
   * @param favorId the identifier favor
   * @return a list of users favour grouped by favor id
   */
  List<UserFavorDTO> findAllUserFavourByFavorId(long favorId);
}
