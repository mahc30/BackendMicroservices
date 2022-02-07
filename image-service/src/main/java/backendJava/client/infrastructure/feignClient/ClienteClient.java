package backendJava.client.infrastructure.feignClient;

import backendJava.client.application.dto.ClienteDTO;
import backendJava.client.infrastructure.fallback.ClienteHystrixFallbackFactory;
import backendJava.client.domain.model.TipoIdentificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cliente-service", fallback = ClienteHystrixFallbackFactory.class)
public interface ClienteClient {
    @GetMapping(value="/clients/{tipoIdentificacionId}/{numeroIdentificacion}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable("tipoIdentificacionId") TipoIdentificacion tipoIdentificacion, @PathVariable("numeroIdentificacion") String numeroIdentificacion);

    @PutMapping(value="/clients/{tipoIdentificacionId}/{numeroIdentificacion}")
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO client, @PathVariable("tipoIdentificacionId") TipoIdentificacion tipoIdentificacion, @PathVariable("numeroIdentificacion") String numeroIdentificacion);
}