package be.brahms.TFE_RentServe.exceptions.picture;

/** This is a general exception for picture errors. */
public class PictureException extends RuntimeException {

  /**
   * Create a new picture exception.
   *
   * @param message the error message
   */
  public PictureException(String message) {
    super(message);
  }
}
