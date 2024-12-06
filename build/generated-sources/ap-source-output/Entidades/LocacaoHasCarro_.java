package Entidades;

import Entidades.Carro;
import Entidades.Locacao;
import Entidades.LocacaoHasCarroPK;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-04T17:35:04", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(LocacaoHasCarro.class)
public class LocacaoHasCarro_ { 

    public static volatile SingularAttribute<LocacaoHasCarro, Integer> preco;
    public static volatile SingularAttribute<LocacaoHasCarro, Carro> carro;
    public static volatile SingularAttribute<LocacaoHasCarro, LocacaoHasCarroPK> locacaoHasCarroPK;
    public static volatile SingularAttribute<LocacaoHasCarro, Locacao> locacao;

}