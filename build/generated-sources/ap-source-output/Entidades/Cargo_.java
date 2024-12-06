package Entidades;

import Entidades.Funcionario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-04T17:35:04", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cargo.class)
public class Cargo_ { 

    public static volatile SingularAttribute<Cargo, Integer> idCargo;
    public static volatile ListAttribute<Cargo, Funcionario> funcionarioList;
    public static volatile SingularAttribute<Cargo, String> nomeCargo;
    public static volatile SingularAttribute<Cargo, String> renda;

}