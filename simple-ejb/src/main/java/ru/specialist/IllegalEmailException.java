package ru.specialist;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class IllegalEmailException extends Exception {
    
    public IllegalEmailException(String msg) {
        super(msg);
    }
    
}
