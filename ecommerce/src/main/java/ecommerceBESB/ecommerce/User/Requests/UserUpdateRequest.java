package ecommerceBESB.ecommerce.User.Requests;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
                                @NotBlank(message = "name should be not blank")
                                String name,

                                @NotBlank(message = "email should be not blank")
                                @Email(message = "email should be valid")
                                String email,

                                @NotBlank(message = "password should be not blank")
                                @Size(message = "password should be between 8 and 16 characters", min = 8, max = 16)
                                String password
) {

}
