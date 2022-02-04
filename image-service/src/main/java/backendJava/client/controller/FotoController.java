package backendJava.client.controller;

import backendJava.client.dto.FotoDTO;
import backendJava.client.client.model.TipoIdentificacion;
import backendJava.client.service.FotoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping(value = "/fotos", consumes = MediaType.ALL_VALUE)
@Validated
public class FotoController {
    @Autowired
    private FotoService fotoService;

    @GetMapping
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<List<FotoDTO>> listFoto(){
        return ResponseEntity.ok(fotoService.listAllFoto());
    }

    @GetMapping(value="/{id}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<FotoDTO> getFoto(@PathVariable("id") String id){
        FotoDTO foto = fotoService.getFoto(id);
        if(foto == null) return ResponseEntity.notFound().build();
        else return  ResponseEntity.ok(foto);
    }

    @PostMapping(value = "/{tipoIdentificacion}/{NumeroIdentificacion}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<FotoDTO> createFoto(@PathVariable("tipoIdentificacion") TipoIdentificacion tipoId, @PathVariable("NumeroIdentificacion") String numeroId, @RequestPart MultipartFile file){
        return ResponseEntity.ok(fotoService.createFoto(tipoId, numeroId, file));
    }

    @DeleteMapping(value="/{tipoIdentificacion}/{NumeroIdentificacion}/{id}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<FotoDTO> deleteFoto(@PathVariable("id") String id, @PathVariable("tipoIdentificacion") TipoIdentificacion tipoId, @PathVariable("NumeroIdentificacion") String numeroId){
        fotoService.deleteFoto(tipoId, numeroId, id);
        return  ResponseEntity.ok().build();
    }

    @PutMapping(value="/{id}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public ResponseEntity<FotoDTO> updateFoto(@PathVariable("id") String id, @RequestPart MultipartFile file){
        return ResponseEntity.ok(fotoService.updateFoto(id, file));
    }


}
