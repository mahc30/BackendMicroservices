package backendJava.client.application.service;

import backendJava.client.application.dto.FotoDTO;
import backendJava.client.domain.model.TipoIdentificacion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FotoService {
    public List<FotoDTO> listAllFoto();

    public FotoDTO getFoto(String id);
    public FotoDTO createFoto(TipoIdentificacion tipoId, String numeroId, MultipartFile file);
    public FotoDTO updateFoto(String id, MultipartFile file);
    public void deleteFoto(TipoIdentificacion tipoId, String numeroId, String id);
}
