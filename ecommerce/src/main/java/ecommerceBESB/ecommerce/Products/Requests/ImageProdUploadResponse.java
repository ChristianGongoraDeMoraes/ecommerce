package ecommerceBESB.ecommerce.Products.Requests;

public class ImageProdUploadResponse {
    private String message;

    public ImageProdUploadResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
