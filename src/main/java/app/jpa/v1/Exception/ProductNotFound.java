package app.jpa.v1.Exception;

public class ProductNotFound extends RuntimeException{

    private String message;

    public ProductNotFound(String message) {
        super(message);
    }
}
