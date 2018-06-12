package ru.specialist.dao;

import java.util.List;
import javax.ejb.Remote;
import ru.specialist.IllegalEmailException;
import ru.specialist.entity.User;

@Remote
public interface UserDAORemote {
    
    User create(User user) throws IllegalEmailException;
    
    void remove(String email);
    
    User find(String email);
    
    List<User> findAll();    
    
}
