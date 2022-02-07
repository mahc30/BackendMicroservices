package backendJava.client.domain.entity;

import backendJava.client.domain.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Fotos")
public class Foto implements Serializable {
    @Id
    @NotNull
    @ApiModelProperty(hidden = true)
    private String id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    private Binary file;

    @Transient
    private Cliente cliente;
    public static Binary convertMultipartToBinary(MultipartFile file){
        Binary bin = null;
        try{
            bin = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return bin;
    }
}
