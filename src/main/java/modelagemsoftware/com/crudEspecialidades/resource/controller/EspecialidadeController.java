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
    @PutMapping("/{id}")
    public ResponseEntity atualizarEspecialidade(@PathVariable Long id, @RequestBody Especialidade especialidade){
        try {
            Especialidade especialidadeAtualizada = service.atualizarEspecialidade(id, especialidade);
            return ResponseEntity.ok(especialidadeAtualizada);

        }catch (RecusroNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarEspecialidade(@PathVariable Long id){
        // 204 NO CONTENT: Código HTTP correto para um DELETE bem-sucedido
        // onde não há corpo de resposta para retornar.
        try {
            service.deletarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (RecusroNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar deletar a espcialidade " + e.getMessage());
        }
    }


}
