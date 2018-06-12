package ru.specialist.dao.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ru.specialist.IllegalEmailException;
import ru.specialist.dao.UserDAOLocal;
import ru.specialist.dao.UserDAORemote;
import ru.specialist.entity.User;

@Stateless(name="UserDAO", mappedName="UserDAO")
public class UserDAOBean implements UserDAORemote, UserDAOLocal {
    
    @PersistenceContext(unitName="mydbPU")
    private EntityManager em;
    
    @EJB
    private EmailSenderBean emailSender;

    @Override
    public User create(User user) throws IllegalEmailException {
        if (em.find(User.class, user.getEmail()) != null) {
            throw new EJBException("user exists");
        }
        if (user.getAddress() != null) {
            em.persist(user.getAddress());            
        }
        em.persist(user);
        emailSender.sendEmail(user.getEmail());
        return user;
    }

    @Override
    public void remove(String email) {
        User user = em.find(User.class, email);
        em.remove(user);
    }

    @Override
    public User find(String email) {
        return em.find(User.class, email);
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        //query.setFirstResult(offset);
        //query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public List<User> findByCity(String city, int offset, int limit) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.address IS NOT NULL AND u.address.city LIKE :city");
        query.setParameter("city", "%" + city + "%");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
}
