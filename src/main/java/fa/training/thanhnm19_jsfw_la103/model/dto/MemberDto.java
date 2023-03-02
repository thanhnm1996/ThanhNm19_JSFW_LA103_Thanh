package fa.training.thanhnm19_jsfw_la103.model.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class MemberDto {
    private String firstName;

    private String lastName;

    private String email;
    @Pattern(regexp="\\d{10}", message="Phone number must be 10 digits")
    private String phone;

    private String description;
}
