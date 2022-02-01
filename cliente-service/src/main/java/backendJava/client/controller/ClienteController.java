package backendJava.client.controller;

import backendJava.client.dto.ClienteDTO;
import backendJava.client.dto.ClienteMapper;
import backendJava.client.entity.Cliente;
import backendJava.client.entity.TipoIdentificacion;
import backendJava.client.service.ClienteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<List<ClienteDTO>> listCliente(){
        return ResponseEntity.ok(clienteService.listAllCliente());
    }

    @GetMapping(value="/{edad}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<List<ClienteDTO>> filterClienteByEdad(@PathVariable("edad") int edad){
        return ResponseEntity.ok(clienteService.findByEdadGreaterThanEqual(edad));
    }

    @GetMapping(value="/{tipoIdentificacionId}/{numeroIdentificacion}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable("tipoIdentificacionId") TipoIdentificacion tipoIdentificacion,@PathVariable("numeroIdentificacion") String numeroIdentificacion){
        return  ResponseEntity.ok(clienteService.findByTipoIdentificacionAndNumeroIdentificacion(
                tipoIdentificacion,
                numeroIdentificacion));
    }

    @PostMapping
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<ClienteDTO> createCliente( @RequestBody ClienteDTO client){
        return ResponseEntity.ok(clienteService.createCliente(ClienteMapper.INSTANCE.clienteDtoToCliente(client)));
    }

    @PutMapping(value="/{tipoIdentificacionId}/{numeroIdentificacion}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO client, @PathVariable("tipoIdentificacionId") TipoIdentificacion tipoIdentificacion,@PathVariable("numeroIdentificacion") String numeroIdentificacion){
        return ResponseEntity.ok(clienteService.updateCliente(ClienteMapper.INSTANCE.clienteDtoToCliente(client)));
    }

    @DeleteMapping(value="/{tipoIdentificacionId}/{numeroIdentificacion}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable("tipoIdentificacionId") TipoIdentificacion tipoIdentificacion,@PathVariable("numeroIdentificacion") String numeroIdentificacion){
        clienteService.deleteCliente(tipoIdentificacion, numeroIdentificacion);
        return  ResponseEntity.ok().build();
    }
}
