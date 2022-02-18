package app.jpa.v1.Utility.Exception;

public class ProductNotFound extends RuntimeException{

    private String message;

    public ProductNotFound(String message) {
        super(message);
        this.message = message;
    }
}
