package Entidades;

import Entidades.Pessoa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-07T13:45:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cidade.class)
public class Cidade_ { 

    public static volatile SingularAttribute<Cidade, String> nomeCidade;
    public static volatile SingularAttribute<Cidade, Integer> idCidade;
    public static volatile ListAttribute<Cidade, Pessoa> pessoaList;

}