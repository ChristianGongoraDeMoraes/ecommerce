package ecommerceBESB.ecommerce.Products.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductSaveRequest(
                                    @NotBlank
                                    String name,

                                    @Positive
                                    Integer price
) {

}
