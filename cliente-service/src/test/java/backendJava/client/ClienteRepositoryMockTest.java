package backendJava.client;

import backendJava.client.domain.entity.Cliente;
import backendJava.client.domain.entity.TipoIdentificacion;
import backendJava.client.infrastructure.repository.ClienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClienteRepositoryMockTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setup(){

    }

    @Test
    public void whenFindByTipoIdentificacion_thenReturnListCliente(){

        Cliente found = clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(TipoIdentificacion.CC, "100203403");

        Assertions.assertThat(found).isNotNull();
        Assertions.assertThat(found.getNumeroIdentificacion()).isEqualTo("100203403");
    }

    @Test
    public void whenFilterByEdad_ThenReturnListCliente(){
        List<Cliente> founds = clienteRepository.findByEdadGreaterThanEqual(20);
        Assertions.assertThat(founds.size()).isEqualTo(3);
    }

}
