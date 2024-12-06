package Entidades;

import Entidades.Cliente;
import Entidades.LocacaoHasCarro;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-04T17:35:04", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Locacao.class)
public class Locacao_ { 

    public static volatile SingularAttribute<Locacao, Date> dataLocacao;
    public static volatile ListAttribute<Locacao, LocacaoHasCarro> locacaoHasCarroList;
    public static volatile SingularAttribute<Locacao, Cliente> clientePessoaCpfPessoa;
    public static volatile SingularAttribute<Locacao, Integer> idLocacao;

}