package modelagemsoftware.com.crudEspecialidades.service;

import modelagemsoftware.com.crudEspecialidades.domain.entity.Especialidade;
import modelagemsoftware.com.crudEspecialidades.exception.RecusroNaoEncontradoException;
import modelagemsoftware.com.crudEspecialidades.infrastructure.repository.EspecialidadeRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {
    private final EspecialidadeRepository repository;

    @Autowired //injeção de dependência - via Construtor.
    public EspecialidadeService(EspecialidadeRepository repository){
        this.repository = repository;
    }

    public Especialidade criarEspecialidade(Especialidade novaEspecialidade) throws IllegalAccessException {

        if (repository.existsByNome(novaEspecialidade.getNome())) {

            throw new IllegalAccessException("O nome da especialidade já existe");
        }
        return repository.save(novaEspecialidade);

    }
    public List<Especialidade> ListarTodasAtivas(){
        return repository.findAllByAtivoTrue();
    }
    public Especialidade buscarPorId(Long id){
        Especialidade especialidade = repository.findById(id).
                orElseThrow(() -> new RecusroNaoEncontradoException("Especialidade não encontrada com ID: " + id));
        if (!especialidade.getAtivo()){
            throw new RecusroNaoEncontradoException("Especialidade não encontrada com ID: " + id);
        }

        return especialidade;
    }

    public Especialidade atualizarEspecialidade(Long id, Especialidade dadosAtualizados) throws IllegalAccessException {
        Especialidade especialidadeExistente = buscarPorId(id);
        String novoNome = dadosAtualizados.getNome();

        if (novoNome != null && !novoNome.equals(especialidadeExistente.getNome())){
            if (repository.existsByNomeAndAtivoTrueAndIdNot(novoNome, id)){
                throw new IllegalAccessException("O nome " + novoNome + "já está em uso por outra especialidade");
            }
            especialidadeExistente.setNome(novoNome);
        }
        if (dadosAtualizados.getDescricao() != null){
            especialidadeExistente.setDescricao(dadosAtualizados.getDescricao());
        }

        return repository.save(especialidadeExistente);
    }

    public void deletarPorId(Long id){

        Especialidade especialidade = buscarPorId(id);

        especialidade.setAtivo(false); //seta a flag para false
        repository.save(especialidade);
    }
    }



