package modelagemsoftware.com.crudEspecialidades.domain.entity;

import jakarta.persistence.*;

@Entity //define a classe como uma tabela no DB
@Table(name = "especialidades")//define o nome da tabela
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//gera um ID automaticamente.
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false)
    private Boolean ativo = true;//marca o registro como inativo

    public Especialidade(){

    }
    public Especialidade(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
