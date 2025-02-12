package ecommerceBESB.ecommerce.Orders.Requests;

import java.util.UUID;

public record OrderSaveRequest(
                                UUID userId,
                                UUID productId
) {

}
