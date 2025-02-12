package ecommerceBESB.ecommerce.Errors.Handler;

import java.util.Map;


public record ErrorResponse(
    Map<String, String> errors
) {

}
