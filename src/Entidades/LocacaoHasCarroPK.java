/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Leonardo Pontin
 */
@Embeddable
public class LocacaoHasCarroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "carro_id_carro")
    private int carroIdCarro;
    @Basic(optional = false)
    @Column(name = "locacao_id_locacao")
    private int locacaoIdLocacao;

    public LocacaoHasCarroPK() {
    }

    public LocacaoHasCarroPK(int carroIdCarro, int locacaoIdLocacao) {
        this.carroIdCarro = carroIdCarro;
        this.locacaoIdLocacao = locacaoIdLocacao;
    }

    public int getCarroIdCarro() {
        return carroIdCarro;
    }

    public void setCarroIdCarro(int carroIdCarro) {
        this.carroIdCarro = carroIdCarro;
    }

    public int getLocacaoIdLocacao() {
        return locacaoIdLocacao;
    }

    public void setLocacaoIdLocacao(int locacaoIdLocacao) {
        this.locacaoIdLocacao = locacaoIdLocacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) carroIdCarro;
        hash += (int) locacaoIdLocacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocacaoHasCarroPK)) {
            return false;
        }
        LocacaoHasCarroPK other = (LocacaoHasCarroPK) object;
        if (this.carroIdCarro != other.carroIdCarro) {
            return false;
        }
        if (this.locacaoIdLocacao != other.locacaoIdLocacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return carroIdCarro + ";" + locacaoIdLocacao;
    }
    
}
