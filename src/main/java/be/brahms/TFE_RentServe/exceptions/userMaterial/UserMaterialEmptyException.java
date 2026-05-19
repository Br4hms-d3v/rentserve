package be.brahms.TFE_RentServe.exceptions.userMaterial;

/** Exception evoked when user material is empty */
public class UserMaterialEmptyException extends UserMaterialException {
  /**
   * Make a new exception when the user material is empty
   *
   * @param message the error message
   */
  public UserMaterialEmptyException(String message) {
    super(message);
  }

  /** This exception is used when a user material is empty */
  public UserMaterialEmptyException() {
    super("La liste est vide");
  }
}
