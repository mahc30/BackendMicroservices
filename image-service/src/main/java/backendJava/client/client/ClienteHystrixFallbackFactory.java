package backendJava.client.client;

import backendJava.client.dto.ClienteDTO;
import backendJava.client.client.model.TipoIdentificacion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteHystrixFallbackFactory implements ClienteClient {

    @Override
    public ResponseEntity<ClienteDTO> getCliente(TipoIdentificacion tipoIdentificacion, String numeroIdentificacion) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ClienteDTO> updateCliente(ClienteDTO client, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion) {
        return ResponseEntity.ok(null);
    }
}
