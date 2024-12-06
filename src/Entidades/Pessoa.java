/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Leonardo Pontin
 */
@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")})
public class Pessoa implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Funcionario funcionario;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf_pessoa")
    private String cpfPessoa;
    @Basic(optional = false)
    @Column(name = "nome_pessoa")
    private String nomePessoa;
    @Basic(optional = false)
    @Column(name = "data_de_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataDeNascimento;
    @Basic(optional = false)
    @Column(name = "codigo_pessoa")
    private String codigoPessoa;
    @JoinColumn(name = "cidade_id_cidade", referencedColumnName = "id_cidade")
    @ManyToOne(optional = false)
    private Cidade cidadeIdCidade;

    public Pessoa() {
    }

    public Pessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public Pessoa(String cpfPessoa, String nomePessoa, Date dataDeNascimento, String codigoPessoa) {
        this.cpfPessoa = cpfPessoa;
        this.nomePessoa = nomePessoa;
        this.dataDeNascimento = dataDeNascimento;
        this.codigoPessoa = codigoPessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(String codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public Cidade getCidadeIdCidade() {
        return cidadeIdCidade;
    }

    public void setCidadeIdCidade(Cidade cidadeIdCidade) {
        this.cidadeIdCidade = cidadeIdCidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpfPessoa != null ? cpfPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.cpfPessoa == null && other.cpfPessoa != null) || (this.cpfPessoa != null && !this.cpfPessoa.equals(other.cpfPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cpfPessoa+";" + nomePessoa + ";" +dataDeNascimento + ";" + codigoPessoa  + ";" + cidadeIdCidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
}
