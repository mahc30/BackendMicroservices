package backendJava.client.infrastructure.fallback;

import backendJava.client.application.dto.ClienteDTO;
import backendJava.client.infrastructure.feignClient.ClienteClient;
import backendJava.client.domain.model.TipoIdentificacion;
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
