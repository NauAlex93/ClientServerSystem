package ru.specialist.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    @OneToMany(mappedBy = "address")
    private Set<User> user;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Address) {
            return ((Address) obj).getId().equals(this.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "ru.specialist.entity.Address[ id=" + id + " ]";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the user
     */
    public Set<User> getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Set<User> user) {
        this.user = user;
    }
    
}
