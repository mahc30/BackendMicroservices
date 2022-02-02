package backendJava.client.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ClienteDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @NotEmpty
    private String nombres;

    @NotNull
    @NotEmpty
    private String apellidos;

    @NotEmpty
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "El número de identificación solo puede contener números") //Validate string is number
    private String numeroIdentificacion;

    @Builder.Default
    private String fotoMongoId = ""; //String identificación de Mongo

    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    private int edad;
    private String ciudad;
    private String tipoIdentificacion;
}
