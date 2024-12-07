package Entidades;

import Entidades.LocacaoHasCarro;
import Entidades.Motor;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-07T13:45:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Carro.class)
public class Carro_ { 

    public static volatile SingularAttribute<Carro, String> nomeCarro;
    public static volatile ListAttribute<Carro, LocacaoHasCarro> locacaoHasCarroList;
    public static volatile SingularAttribute<Carro, Integer> idCarro;
    public static volatile SingularAttribute<Carro, Motor> motorIdMotor;

}