/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Leonardo Pontin
 */
@Entity
@Table(name = "funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_cpf_pessoa")
    private String pessoaCpfPessoa;
    @JoinColumn(name = "cargo_id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargo cargoIdCargo;
    @JoinColumn(name = "pessoa_cpf_pessoa", referencedColumnName = "cpf_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public Funcionario() {
    }

    public Funcionario(String pessoaCpfPessoa) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
    }

    public String getPessoaCpfPessoa() {
        return pessoaCpfPessoa;
    }

    public void setPessoaCpfPessoa(String pessoaCpfPessoa) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
    }

    public Cargo getCargoIdCargo() {
        return cargoIdCargo;
    }

    public void setCargoIdCargo(Cargo cargoIdCargo) {
        this.cargoIdCargo = cargoIdCargo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaCpfPessoa != null ? pessoaCpfPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.pessoaCpfPessoa == null && other.pessoaCpfPessoa != null) || (this.pessoaCpfPessoa != null && !this.pessoaCpfPessoa.equals(other.pessoaCpfPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Funcionario[ pessoaCpfPessoa=" + pessoaCpfPessoa + " ]";
    }
    
}
