package be.brahms.TFE_RentServe.exceptions.picture;

/** Exception evoked when the picture doesn't exist. */
public class PictureNotFound extends PictureException {

  /**
   * Make a new exception when the picture doesn't exist.
   *
   * @param message the error message
   */
  public PictureNotFound(String message) {
    super(message);
  }

  /** Create a picture not found exception. Send a message for a picture doesn't found */
  public PictureNotFound() {
    super("Aucune photo n'a été trouvée");
  }
}
