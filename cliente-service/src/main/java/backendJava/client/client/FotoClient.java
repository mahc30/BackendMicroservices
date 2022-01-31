package backendJava.client.client;

import backendJava.client.dto.FotoDTO;
import backendJava.client.entity.TipoIdentificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "image-service")
public interface FotoClient {
    @DeleteMapping(value="/fotos/{tipoIdentificacion}/{NumeroIdentificacion}/{id}")
    public ResponseEntity<FotoDTO> deleteFoto(@PathVariable("id") String id, @PathVariable("tipoIdentificacion") TipoIdentificacion tipoId, @PathVariable("NumeroIdentificacion") String numeroId);
}
