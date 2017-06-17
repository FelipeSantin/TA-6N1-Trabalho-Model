package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "pacientes")
public class Pacientes implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_pacientes", sequenceName = "seq_pacientes_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pacientes", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ser em branco")
    @Length(max = 50, message = "O email não pode ter mais que {max} caracteres")
    @Column(name = "email", length = 50, nullable = false)    
    private String email;
    @NotNull(message = "O nascimento não pode ser nulo")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;
    @NotNull(message = "O RG não pode ser nulo")
    @NotBlank(message = "O RG não pode ser em branco")
    @Column(name = "rg", nullable = false)
    private String rg;
    @NotNull(message = "O Numero do Cartao do SUS não pode ser nulo")
    @NotBlank(message = "O Numero do Cartao do SUS não pode ser em branco")
    @Column(name = "nrcartaosus", nullable = false)
    private String nrCartaoSUS;
    @NotNull(message = "O Tipo sanguineo não pode ser nulo")
    @NotBlank(message = "O Tipo sanguineo não pode ser em branco")
    @Column(name = "tiposanguineo", length = 20, nullable = false)
    private String tiposanguineo;
    @ManyToMany
    @JoinTable(name = "unidadesPacientes",
            joinColumns
            = @JoinColumn(name = "pacientes", referencedColumnName = "id", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "unidadesatendimento", referencedColumnName = "id", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_unidadesPacientes",
                        columnNames = {"pacientes", "unidadesatendimento"})})
    private List<UnidadesAtendimento> unidadesPacientes = new ArrayList<>();

    public Pacientes() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNrCartaoSUS() {
        return nrCartaoSUS;
    }

    public void setNrCartaoSUS(String nrCartaoSUS) {
        this.nrCartaoSUS = nrCartaoSUS;
    }

    public String getTiposanguineo() {
        return tiposanguineo;
    }

    public void setTiposanguineo(String tiposanguineo) {
        this.tiposanguineo = tiposanguineo;
    }

    public List<UnidadesAtendimento> getUnidadesPacientes() {
        return unidadesPacientes;
    }

    public void setUnidadesPacientes(List<UnidadesAtendimento> unidadesPacientes) {
        this.unidadesPacientes = unidadesPacientes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Pacientes other = (Pacientes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}