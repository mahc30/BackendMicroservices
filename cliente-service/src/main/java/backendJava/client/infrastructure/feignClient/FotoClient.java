package backendJava.client.infrastructure.feignClient;

import backendJava.client.application.dto.FotoDTO;
import backendJava.client.domain.entity.TipoIdentificacion;
import backendJava.client.infrastructure.fallback.FotoHystrixFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "image-service", fallback = FotoHystrixFallbackFactory.class)
public interface FotoClient {
    @DeleteMapping(value="/fotos/{tipoIdentificacion}/{NumeroIdentificacion}/{id}")
    public ResponseEntity<FotoDTO> deleteFoto(@PathVariable("id") String id, @PathVariable("tipoIdentificacion") TipoIdentificacion tipoId, @PathVariable("NumeroIdentificacion") String numeroId);
}
