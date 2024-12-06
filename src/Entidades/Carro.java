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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Leonardo Pontin
 */
@Entity
@Table(name = "carro")
@NamedQueries({
    @NamedQuery(name = "Carro.findAll", query = "SELECT c FROM Carro c")})
public class Carro implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carro")
    private List<LocacaoHasCarro> locacaoHasCarroList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_carro")
    private Integer idCarro;
    @Basic(optional = false)
    @Column(name = "nome_carro")
    private String nomeCarro;
    @JoinColumn(name = "motor_id_motor", referencedColumnName = "id_motor")
    @ManyToOne(optional = false)
    private Motor motorIdMotor;

    public Carro() {
    }

    public Carro(Integer idCarro) {
        this.idCarro = idCarro;
    }

    public Carro(Integer idCarro, String nomeCarro) {
        this.idCarro = idCarro;
        this.nomeCarro = nomeCarro;
    }

    public Integer getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Integer idCarro) {
        this.idCarro = idCarro;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    public Motor getMotorIdMotor() {
        return motorIdMotor;
    }

    public void setMotorIdMotor(Motor motorIdMotor) {
        this.motorIdMotor = motorIdMotor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarro != null ? idCarro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carro)) {
            return false;
        }
        Carro other = (Carro) object;
        if ((this.idCarro == null && other.idCarro != null) || (this.idCarro != null && !this.idCarro.equals(other.idCarro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idCarro+";" + nomeCarro + ";" +motorIdMotor;
    }

    public List<LocacaoHasCarro> getLocacaoHasCarroList() {
        return locacaoHasCarroList;
    }

    public void setLocacaoHasCarroList(List<LocacaoHasCarro> locacaoHasCarroList) {
        this.locacaoHasCarroList = locacaoHasCarroList;
    }
    
}
