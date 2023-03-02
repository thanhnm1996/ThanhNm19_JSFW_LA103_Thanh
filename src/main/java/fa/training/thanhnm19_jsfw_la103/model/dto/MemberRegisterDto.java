package fa.training.thanhnm19_jsfw_la103.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberRegisterDto {
    @NotBlank(message = "{error.required}")
    private String userName;
    @NotBlank(message = "{error.required}")
    private String password;
    @NotBlank(message = "{error.required}")
    private String rePassword;
    @NotBlank(message = "{error.required}")
    @Email(message="Invalid email format")
    private String email;
}
