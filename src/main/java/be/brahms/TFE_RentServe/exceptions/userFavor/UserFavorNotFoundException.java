package be.brahms.TFE_RentServe.exceptions.userFavor;

/** Exception evoked when user favor doesn't exist */
public class UserFavorNotFoundException extends UserFavorException {
  /**
   * Make a new exception when the favor doesn't exist.
   *
   * @param message the error message
   */
  public UserFavorNotFoundException(String message) {
    super(message);
  }

  /** This exception is used when a user favor is not found */
  public UserFavorNotFoundException() {
    super("Le service n'a pas été retrouvé");
  }
}
