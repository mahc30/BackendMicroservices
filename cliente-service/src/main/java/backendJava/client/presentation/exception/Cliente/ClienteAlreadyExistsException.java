package backendJava.client.presentation.exception.Cliente;

import backendJava.client.domain.entity.TipoIdentificacion;

public class ClienteAlreadyExistsException extends RuntimeException{
    public ClienteAlreadyExistsException(TipoIdentificacion tipoId, String numeroId) {
        super(String.format("Client with TypeId: %s number: %s Already Exists", tipoId, numeroId));
    }
}
