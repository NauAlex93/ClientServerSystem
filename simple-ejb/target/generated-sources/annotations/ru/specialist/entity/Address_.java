package ru.specialist.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.specialist.entity.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-12T23:33:48")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, Long> id;
    public static volatile SetAttribute<Address, User> user;

}