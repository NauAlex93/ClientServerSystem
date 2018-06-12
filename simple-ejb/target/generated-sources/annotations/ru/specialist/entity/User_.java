package ru.specialist.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.specialist.entity.Address;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-12T23:33:48")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Address> address;
    public static volatile SingularAttribute<User, Integer> birthYear;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Date> birthDate;
    public static volatile SingularAttribute<User, String> email;

}