package be.brahms.TFE_RentServe.exceptions.userFavor;

/** Exception evoked when user favor is empty */
public class UserFavourEmptyException extends UserFavorException {
  /**
   * Make a new exception when the favor is empty
   *
   * @param message the error message
   */
  public UserFavourEmptyException(String message) {
    super(message);
  }

  /** This exception is used when a user favour is empty */
  public UserFavourEmptyException() {
    super("La list est vide");
  }
}
