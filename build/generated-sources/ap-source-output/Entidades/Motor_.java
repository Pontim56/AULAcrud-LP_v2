package Entidades;

import Entidades.Carro;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-04T17:35:04", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Motor.class)
public class Motor_ { 

    public static volatile SingularAttribute<Motor, String> tipoMotor;
    public static volatile ListAttribute<Motor, Carro> carroList;
    public static volatile SingularAttribute<Motor, Integer> idMotor;

}