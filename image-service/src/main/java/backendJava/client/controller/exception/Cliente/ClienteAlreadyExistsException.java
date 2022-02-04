package backendJava.client.controller.exception.Cliente;


import backendJava.client.client.model.TipoIdentificacion;

public class ClienteAlreadyExistsException extends RuntimeException{
    public ClienteAlreadyExistsException(TipoIdentificacion tipoId, String numeroId) {
        super(String.format("Client with TypeId: %s number: %s Already Exists", tipoId, numeroId));
    }
}
