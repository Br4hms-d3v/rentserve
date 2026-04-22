package be.brahms.TFE_RentServe.exceptions.picture;

public class PictureNotFound extends PictureException {
  public PictureNotFound(String message) {
    super(message);
  }

  public PictureNotFound() {
    super("Aucune photo n'a été trouvée");
  }
}
