package backendJava.client.client;

import backendJava.client.dto.ClienteDTO;
import backendJava.client.model.TipoIdentificacion;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteHystrixFallbackFactory implements ClienteClient {

    @Override
    public ResponseEntity<ClienteDTO> getCliente(TipoIdentificacion tipoIdentificacion, String numeroIdentificacion) {
        return ResponseEntity.ok(
                ClienteDTO.builder().
                        nombres("Error")
                        .apellidos("Error")
                        .ciudad("Error")
                        .tipoIdentificacion("Error")
                        .numeroIdentificacion("Error")
                        .fotoMongoId("Error")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ClienteDTO> updateCliente(ClienteDTO client, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion) {
        return ResponseEntity.ok(
                ClienteDTO.builder().
                        nombres("Error")
                        .apellidos("Error")
                        .ciudad("Error")
                        .tipoIdentificacion("Error")
                        .numeroIdentificacion("Error")
                        .fotoMongoId("Error")
                        .build()
        );
    }
}
