package modelagemsoftware.com.crudEspecialidades.infrastructure.repository;

import modelagemsoftware.com.crudEspecialidades.domain.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    //consulta SQL automaticamente baseado no nome do método!
    boolean existsByNome(String nome);

    //método soft delete:
    //o spring interpreta este nome como: "select * FROM especialidades WHERE ativo= TRUE"
    List<Especialidade> findAllByAtivoTrue();
    boolean existsByNomeAndAtivoTrueAndIdNot(String nome, Long id);
}

