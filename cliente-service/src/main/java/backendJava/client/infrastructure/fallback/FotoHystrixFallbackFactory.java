package backendJava.client.infrastructure.fallback;

import backendJava.client.application.dto.FotoDTO;
import backendJava.client.domain.entity.TipoIdentificacion;
import backendJava.client.infrastructure.feignClient.FotoClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FotoHystrixFallbackFactory implements FotoClient {
    @Override
    @HystrixCommand
    public ResponseEntity<FotoDTO> deleteFoto(String id, TipoIdentificacion tipoId, String numeroId) {
        return null;
    }
}
