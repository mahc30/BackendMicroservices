package backendJava.client.infrastructure.repository;

import backendJava.client.domain.entity.Cliente;
import backendJava.client.domain.entity.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByTipoIdentificacionAndNumeroIdentificacion(TipoIdentificacion tipoId, String numeroIdentificacion);

    public List<Cliente> findByEdadGreaterThanEqual(int edad);
}
