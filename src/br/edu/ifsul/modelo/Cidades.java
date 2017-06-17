
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "cidades")
public class Cidades implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_cidades", sequenceName = "seq_cidades_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cidades", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode estar em branco")
    @Column(name = "nome", nullable = false)     
    private String nome;
    @Column(name = "estado", nullable = false)     
    private String estado;
    
    @OneToMany(mappedBy = "cidades", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UnidadesAtendimento> unidadesAtendimentos = new ArrayList<>();
    
    public Cidades() {
    }
   
    public void adicionarUnidadeAtendimento(UnidadesAtendimento obj){
        obj.setCidades(this);
        this.unidadesAtendimentos.add(obj);
    }
    
    public void removerUnidadeAtendimento(int idx){
        this.unidadesAtendimentos.remove(idx);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<UnidadesAtendimento> getUnidadesAtendimentos() {
        return unidadesAtendimentos;
    }

    public void setUnidadesAtendimentos(List<UnidadesAtendimento> unidadesAtendimentos) {
        this.unidadesAtendimentos = unidadesAtendimentos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cidades other = (Cidades) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}