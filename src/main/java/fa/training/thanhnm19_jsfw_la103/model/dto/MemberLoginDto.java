package fa.training.thanhnm19_jsfw_la103.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginDto {
    private String email;
    private String password;
}
