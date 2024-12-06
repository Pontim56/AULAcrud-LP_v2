/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Leonardo Pontin
 */

@Entity
@Table(name = "locacao_has_carro")
@NamedQueries({
    @NamedQuery(name = "LocacaoHasCarro.findAll", query = "SELECT l FROM LocacaoHasCarro l")})

public class LocacaoHasCarro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocacaoHasCarroPK locacaoHasCarroPK;
    @Basic(optional = false)
    @Column(name = "preco")
    private int preco;
    @JoinColumn(name = "carro_id_carro", referencedColumnName = "id_carro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Carro carro;
    @JoinColumn(name = "locacao_id_locacao", referencedColumnName = "id_locacao", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Locacao locacao;

    public LocacaoHasCarro() {
    }

    public LocacaoHasCarro(LocacaoHasCarroPK locacaoHasCarroPK) {
        this.locacaoHasCarroPK = locacaoHasCarroPK;
    }

    public LocacaoHasCarro(LocacaoHasCarroPK locacaoHasCarroPK, int preco) {
        this.locacaoHasCarroPK = locacaoHasCarroPK;
        this.preco = preco;
    }

    public LocacaoHasCarro(int carroIdCarro, int locacaoIdLocacao) {
        this.locacaoHasCarroPK = new LocacaoHasCarroPK(carroIdCarro, locacaoIdLocacao);
    }

    public LocacaoHasCarroPK getLocacaoHasCarroPK() {
        return locacaoHasCarroPK;
    }

    public void setLocacaoHasCarroPK(LocacaoHasCarroPK locacaoHasCarroPK) {
        this.locacaoHasCarroPK = locacaoHasCarroPK;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locacaoHasCarroPK != null ? locacaoHasCarroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocacaoHasCarro)) {
            return false;
        }
        LocacaoHasCarro other = (LocacaoHasCarro) object;
        if ((this.locacaoHasCarroPK == null && other.locacaoHasCarroPK != null) || (this.locacaoHasCarroPK != null && !this.locacaoHasCarroPK.equals(other.locacaoHasCarroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  locacaoHasCarroPK +";"+preco ;
    }
    
}
