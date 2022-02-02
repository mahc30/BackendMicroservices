package backendJava.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FotoDTO {
    @Id
    @NotNull
    @ApiModelProperty(hidden = true)
    private String id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    private Binary file;
}
