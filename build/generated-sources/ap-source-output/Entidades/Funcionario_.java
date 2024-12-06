package Entidades;

import Entidades.Cargo;
import Entidades.Pessoa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-04T17:35:04", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, Pessoa> pessoa;
    public static volatile SingularAttribute<Funcionario, Cargo> cargoIdCargo;
    public static volatile SingularAttribute<Funcionario, String> pessoaCpfPessoa;

}