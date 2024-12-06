/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Leonardo Pontin
 */
@Entity
@Table(name = "locacao")
@NamedQueries({
    @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l")})
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_locacao")
    private Integer idLocacao;
    @Basic(optional = false)
    @Column(name = "data_locacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLocacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locacao")
    private List<LocacaoHasCarro> locacaoHasCarroList;
    @JoinColumn(name = "cliente_pessoa_cpf_pessoa", referencedColumnName = "pessoa_cpf_pessoa")
    @ManyToOne(optional = false)
    private Cliente clientePessoaCpfPessoa;

    public Locacao() {
    }

    public Locacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Locacao(Integer idLocacao, Date dataLocacao) {
        this.idLocacao = idLocacao;
        this.dataLocacao = dataLocacao;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public List<LocacaoHasCarro> getLocacaoHasCarroList() {
        return locacaoHasCarroList;
    }

    public void setLocacaoHasCarroList(List<LocacaoHasCarro> locacaoHasCarroList) {
        this.locacaoHasCarroList = locacaoHasCarroList;
    }

    public Cliente getClientePessoaCpfPessoa() {
        return clientePessoaCpfPessoa;
    }

    public void setClientePessoaCpfPessoa(Cliente clientePessoaCpfPessoa) {
        this.clientePessoaCpfPessoa = clientePessoaCpfPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocacao != null ? idLocacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locacao)) {
            return false;
        }
        Locacao other = (Locacao) object;
        if ((this.idLocacao == null && other.idLocacao != null) || (this.idLocacao != null && !this.idLocacao.equals(other.idLocacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idLocacao+";"+dataLocacao+";"+clientePessoaCpfPessoa;
    }
    
}
