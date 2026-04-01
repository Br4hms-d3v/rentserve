package be.brahms.TFE_RentServe.exceptions.material;

/** Exception evoked when the material not found */
public class MaterialNotFoundException extends MaterialException {
  /**
   * Make a new exception when the material is not found
   *
   * @param message the error message
   */
  public MaterialNotFoundException(String message) {
    super(message);
  }

  /** This exception is used when the material is not founded */
  public MaterialNotFoundException() {
    super("The material is not founded");
  }
}
