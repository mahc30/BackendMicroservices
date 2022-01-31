package backendJava.client.service;

import backendJava.client.client.ClienteClient;
import backendJava.client.dto.ClienteDTO;
import backendJava.client.dto.ClienteMapper;
import backendJava.client.dto.FotoDTO;
import backendJava.client.dto.FotoMapper;
import backendJava.client.entity.Foto;
import backendJava.client.exception.Cliente.ClienteNotFoundException;
import backendJava.client.exception.Foto.FotoNotFoundException;
import backendJava.client.model.Cliente;
import backendJava.client.model.TipoIdentificacion;
import backendJava.client.repository.FotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FotoServiceImpl implements FotoService{
    private final FotoRepository fotoRepository;

    @Autowired
    ClienteClient clienteClient;

    @Override
    public List<FotoDTO> listAllFoto() {
        return FotoMapper.INSTANCE.mapEntityListToDtoList(fotoRepository.findAll());
    }

    @Override
    public FotoDTO getFoto(String id) {
        return FotoMapper.INSTANCE.fotoToFotoDto(fotoRepository.findById(id).orElse(null));
    }

    @Override
    public FotoDTO createFoto(TipoIdentificacion tipoId, String numeroId, MultipartFile file) {

        Cliente clienteDB = ClienteMapper.INSTANCE.clienteDtoToCliente(clienteClient.getCliente(tipoId, numeroId).getBody());
        if(clienteDB == null) throw new ClienteNotFoundException(tipoId, numeroId);

        Foto foto = Foto.builder().file(Foto.convertMultipartToBinary(file)).build();

        if(!clienteDB.getFotoMongoId().isEmpty()) this.deleteFoto(tipoId, numeroId, clienteDB.getFotoMongoId());

        foto = fotoRepository.save(foto);
        clienteDB.setFotoMongoId(foto.getId());
        clienteClient.updateCliente(ClienteMapper.INSTANCE.clienteToClienteDto(clienteDB), clienteDB.getTipoIdentificacion(), clienteDB.getNumeroIdentificacion());

        return FotoMapper.INSTANCE.fotoToFotoDto(foto);
    }

    @Override
    public void deleteFoto(TipoIdentificacion tipoId, String numeroId, String id) {
        Cliente clienteDB = ClienteMapper.INSTANCE.clienteDtoToCliente(clienteClient.getCliente(tipoId, numeroId).getBody());
        if(clienteDB == null) throw new ClienteNotFoundException(tipoId, numeroId);

        Foto foto = FotoMapper.INSTANCE.fotoDtoToFoto(this.getFoto(id));
        if(foto == null) throw new FotoNotFoundException(id);
        fotoRepository.deleteById(foto.getId());

        clienteDB.setFotoMongoId("");
        clienteClient.updateCliente(ClienteMapper.INSTANCE.clienteToClienteDto(clienteDB), clienteDB.getTipoIdentificacion(), clienteDB.getNumeroIdentificacion());
    }

    @Override
    public FotoDTO updateFoto(String id, MultipartFile file) {

        Foto fotoDB = FotoMapper.INSTANCE.fotoDtoToFoto(this.getFoto(id));
        if(fotoDB == null) throw new FotoNotFoundException(id);

        fotoDB.setFile(Foto.convertMultipartToBinary(file));
        fotoDB = fotoRepository.save(fotoDB);

        return FotoMapper.INSTANCE.fotoToFotoDto(fotoDB);
    }

}
