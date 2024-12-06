package Entidades;

import Entidades.Cidade;
import Entidades.Cliente;
import Entidades.Funcionario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-04T17:35:04", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ { 

    public static volatile SingularAttribute<Pessoa, Cliente> cliente;
    public static volatile SingularAttribute<Pessoa, String> codigoPessoa;
    public static volatile SingularAttribute<Pessoa, String> nomePessoa;
    public static volatile SingularAttribute<Pessoa, Funcionario> funcionario;
    public static volatile SingularAttribute<Pessoa, Cidade> cidadeIdCidade;
    public static volatile SingularAttribute<Pessoa, Date> dataDeNascimento;
    public static volatile SingularAttribute<Pessoa, String> cpfPessoa;

}