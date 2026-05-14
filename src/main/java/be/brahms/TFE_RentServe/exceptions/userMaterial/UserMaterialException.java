package be.brahms.TFE_RentServe.exceptions.userMaterial;

/** This is a general exception for user material errors */
public class UserMaterialException extends RuntimeException {
  /**
   * Create a new message user material exception
   *
   * @param message the error message
   */
  public UserMaterialException(String message) {
    super(message);
  }
}
