package ecommerceBESB.ecommerce.User.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLogin(    
                            @NotNull(message = "Email should be not null")
                            @Email(message = "Email should be valid")
                            String email,

                            @NotNull(message = "Password should be not null")
                            String password

) {
}  