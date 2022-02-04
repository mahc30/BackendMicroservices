package backendJava.client.client.model;

import lombok.Data;
import org.bson.types.Binary;

@Data
public class Foto {
    private String id;
    private Binary file;
}
