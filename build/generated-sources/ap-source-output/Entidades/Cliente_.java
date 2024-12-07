package Entidades;

import Entidades.Locacao;
import Entidades.Pessoa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-07T13:45:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Pessoa> pessoa;
    public static volatile ListAttribute<Cliente, Locacao> locacaoList;
    public static volatile SingularAttribute<Cliente, String> pessoaCpfPessoa;

}