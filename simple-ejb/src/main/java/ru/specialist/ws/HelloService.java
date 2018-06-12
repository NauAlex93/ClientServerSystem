package ru.specialist.ws;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName="helloService", name="hello")
@Stateless
public class HelloService {
    
    @WebMethod
    public String sayHello() {
        return "Hello, WebService!";
    }
    
}
