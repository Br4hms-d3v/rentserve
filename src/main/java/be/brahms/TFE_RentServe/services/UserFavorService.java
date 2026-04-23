package be.brahms.TFE_RentServe.services;

import be.brahms.TFE_RentServe.models.dtos.userFavor.UserFavorByIdDTO;
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

  /**
   * This method get a list of user favor by ID
   *
   * @param id the identifier of user favor
   * @return a detail about the user favor
   */
  UserFavorByIdDTO findUserFavorById(long id);

  /**
   * This method get a list of user favour by User ID
   *
   * @param userId the identifier of user
   * @return a list of users favour grouped by user id
   */
  List<UserFavorDTO> findAllUserFavourByUserId(long userId);
}
