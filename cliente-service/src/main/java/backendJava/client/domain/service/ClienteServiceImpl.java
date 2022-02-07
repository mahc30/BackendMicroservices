package backendJava.client.domain.service;

import backendJava.client.application.service.ClienteService;
import backendJava.client.infrastructure.feignClient.FotoClient;
import backendJava.client.application.dto.ClienteDTO;
import backendJava.client.application.mapper.ClienteMapper;
import backendJava.client.domain.entity.Cliente;
import backendJava.client.domain.entity.TipoIdentificacion;
import backendJava.client.presentation.exception.Cliente.ClienteAlreadyExistsException;
import backendJava.client.presentation.exception.Cliente.ClienteNotFoundException;
import backendJava.client.infrastructure.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    FotoClient fotoClient;

    @Override
    public List<ClienteDTO> listAllCliente() {
        return ClienteMapper.INSTANCE.mapEntityListToDtoList(clienteRepository.findAll());
    }

    @Override
    public ClienteDTO getCliente(Long id) {
        return ClienteMapper.INSTANCE.clienteToClienteDto(clienteRepository.findById(id).orElse(null));
    }

    @Override
    public ClienteDTO createCliente(Cliente cliente) {
        Cliente clienteDB = clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(cliente.getTipoIdentificacion(), cliente.getNumeroIdentificacion());
        if(clienteDB != null) throw new ClienteAlreadyExistsException(clienteDB.getTipoIdentificacion(), clienteDB.getNumeroIdentificacion());
        return ClienteMapper.INSTANCE.clienteToClienteDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO updateCliente(Cliente cliente) {

        Cliente clienteDB = clienteRepository.findById(cliente.getId()).orElse(null);
        if(clienteDB == null) throw new ClienteNotFoundException(cliente.getTipoIdentificacion(), cliente.getNumeroIdentificacion());

        clienteDB.setNombres(cliente.getNombres());
        clienteDB.setApellidos(cliente.getApellidos());
        clienteDB.setEdad(cliente.getEdad());
        clienteDB.setCiudad(cliente.getCiudad());
        clienteDB.setTipoIdentificacion(cliente.getTipoIdentificacion());
        clienteDB.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        clienteDB.setFotoMongoId(cliente.getFotoMongoId());

        return ClienteMapper.INSTANCE.clienteToClienteDto(clienteRepository.save(clienteDB));
    }

    @Override
    public void deleteCliente(TipoIdentificacion tipoIdentificacion, String numeroIdentificacion) {

        Cliente clienteDB = clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoIdentificacion, numeroIdentificacion);

        if(clienteDB == null) throw new ClienteNotFoundException(tipoIdentificacion, numeroIdentificacion);

        if(!clienteDB.getFotoMongoId().isEmpty()) fotoClient.deleteFoto(clienteDB.getFotoMongoId(), tipoIdentificacion, numeroIdentificacion);
        clienteRepository.deleteById(clienteDB.getId());
    }

    @Override
    public ClienteDTO findByTipoIdentificacionAndNumeroIdentificacion(TipoIdentificacion tipoId, String numeroId) {
        return ClienteMapper.INSTANCE.clienteToClienteDto(
                clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoId, numeroId)
        );
    }

    @Override
    public List<ClienteDTO> findByEdadGreaterThanEqual(int edad) {
        return ClienteMapper.INSTANCE.mapEntityListToDtoList(clienteRepository.findByEdadGreaterThanEqual(edad));
    }
}
