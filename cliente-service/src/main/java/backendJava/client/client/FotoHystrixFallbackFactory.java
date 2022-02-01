package backendJava.client.client;

import backendJava.client.dto.FotoDTO;
import backendJava.client.entity.TipoIdentificacion;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FotoHystrixFallbackFactory implements FotoClient{
    @Override
    @HystrixCommand
    public ResponseEntity<FotoDTO> deleteFoto(String id, TipoIdentificacion tipoId, String numeroId) {
        return ResponseEntity.ok(FotoDTO.builder().id("null").file(null).build());
    }
}
