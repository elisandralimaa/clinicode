package modelagemsoftware.com.crudEspecialidades.resource.controller;

import modelagemsoftware.com.crudEspecialidades.domain.entity.Especialidade;
import modelagemsoftware.com.crudEspecialidades.exception.RecusroNaoEncontradoException;
import modelagemsoftware.com.crudEspecialidades.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    private final EspecialidadeService service;

    @Autowired//injeção de dependência
    public EspecialidadeController(EspecialidadeService service){
        this.service = service;
    }

    @PostMapping// CRATE
    public ResponseEntity cadastrarEspecialidade(@RequestBody Especialidade especialidade){
        try{
            Especialidade novaEspecialidade = service.criarEspecialidade(especialidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(novaEspecialidade);
        } catch (IllegalAccessException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping//listar todos os recursos
    public ResponseEntity<List<Especialidade>> listarTodos(){
        List<Especialidade> especialidades = service.ListarTodasAtivas();

        return ResponseEntity.ok(especialidades);
    }
    @GetMapping("/{id}")//buscar recurso especifico por ID
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try { //chama o service
            Especialidade especialidade = service.buscarPorId(id);
            //retorna RESTful:200 ok
            return ResponseEntity.ok(especialidade);
        }catch (RecusroNaoEncontradoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
