package fa.training.thanhnm19_jsfw_la103.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ContentDto {
    private Long id;

    @NotBlank(message = "{error.required}")
    private String title;
    @NotBlank(message = "{error.required}")
    private String brief;

    private String content;
}
