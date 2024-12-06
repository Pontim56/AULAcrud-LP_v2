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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Leonardo Pontin
 */
@Entity
@Table(name = "motor")
@NamedQueries({
    @NamedQuery(name = "Motor.findAll", query = "SELECT m FROM Motor m")})
public class Motor implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motorIdMotor")
    private List<Carro> carroList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_motor")
    private Integer idMotor;
    @Basic(optional = false)
    @Column(name = "tipo_motor")
    private String tipoMotor;

    public Motor() {
    }

    public Motor(Integer idMotor) {
        this.idMotor = idMotor;
    }

    public Motor(Integer idMotor, String tipoMotor) {
        this.idMotor = idMotor;
        this.tipoMotor = tipoMotor;
    }

    public Integer getIdMotor() {
        return idMotor;
    }

    public void setIdMotor(Integer idMotor) {
        this.idMotor = idMotor;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotor != null ? idMotor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motor)) {
            return false;
        }
        Motor other = (Motor) object;
        if ((this.idMotor == null && other.idMotor != null) || (this.idMotor != null && !this.idMotor.equals(other.idMotor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idMotor + ";" + tipoMotor;
    }

    public List<Carro> getCarroList() {
        return carroList;
    }

    public void setCarroList(List<Carro> carroList) {
        this.carroList = carroList;
    }

}
