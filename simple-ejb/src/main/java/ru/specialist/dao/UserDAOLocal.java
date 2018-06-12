package ru.specialist.dao;

import java.util.List;
import javax.ejb.Local;
import ru.specialist.entity.User;

@Local
public interface UserDAOLocal {
    
    User find(String email);
    
    List<User> findByCity(String city, int offset, int limit);
    
}
