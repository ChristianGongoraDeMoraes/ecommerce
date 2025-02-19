package ecommerceBESB.ecommerce.User.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegister(

                            @NotBlank(message = "name should be not blank")
                            String name,

                          
                            @NotBlank(message = "password should be not blank")
                            @Size(message = "password should be between 8 and 16 characters", min = 8, max = 16)
                            String password,

                            @NotBlank(message = "email should be not blank")
                            @Email(message = "email should be valid")
                            String email

                            )
{

}
