package backendJava.client.domain.model;

import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String nombres;
    private String apellidos;
    private String numeroIdentificacion;
    private String fotoMongoId = ""; //String identificación de Mongo
    private int edad;
    private Ciudad ciudad;
    private TipoIdentificacion tipoIdentificacion;
}
