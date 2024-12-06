/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Leonardo Pontin
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientePessoaCpfPessoa")
    private List<Locacao> locacaoList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_cpf_pessoa")
    private String pessoaCpfPessoa;
    @JoinColumn(name = "pessoa_cpf_pessoa", referencedColumnName = "cpf_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public Cliente() {
    }

    public Cliente(String pessoaCpfPessoa) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
    }

    public String getPessoaCpfPessoa() {
        return pessoaCpfPessoa;
    }

    public void setPessoaCpfPessoa(String pessoaCpfPessoa) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.pessoaCpfPessoa == null && other.pessoaCpfPessoa != null) || (this.pessoaCpfPessoa != null && !this.pessoaCpfPessoa.equals(other.pessoaCpfPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pessoaCpfPessoa+"";
    }

    public List<Locacao> getLocacaoList() {
        return locacaoList;
    }

    public void setLocacaoList(List<Locacao> locacaoList) {
        this.locacaoList = locacaoList;
    }
    
}
