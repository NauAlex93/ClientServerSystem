package ru.specialist.client;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ru.specialist.HelloRemote;

/**
 * Удаленный клиент.
 * @author m.kachalov
 */
public class Main {
    
    public static String ORB_ADDRESS = "127.0.0.1";
    public static String ORB_PORT = "3700";
    public static String JNDI_URL = "java:global/simple-ear-1.0/ru.specialist-simple-ejb-1.0/HelloBean!ru.specialist.HelloRemote";
    
    public static void main( String[] args ) throws Exception {
        readProperties(args);
        InitialContext ctx = createContext(ORB_ADDRESS, ORB_PORT);        
        HelloRemote hBean = (HelloRemote) ctx.lookup(JNDI_URL);
        System.out.println(hBean.sayHello());
    }
    
    private static void readProperties(String[] arg) {
        if (arg.length > 0) {
            ORB_ADDRESS = arg[0];
        }
        if (arg.length > 1) {
            ORB_PORT = arg[1];
        }
        if (arg.length > 2) {
            JNDI_URL = arg[2];
        }
    }
    
    private static InitialContext createContext(String host, String port) throws NamingException {        
        Properties props = new Properties();
        System.out.println("host: " + host + " port: " + port);
  
        props.setProperty("java.naming.factory.initial",
                    "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs",
                    "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state",
                    "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.CORBA.ORBInitialHost", host);
        props.setProperty("org.omg.CORBA.ORBInitialPort", port);
  
        return new InitialContext(props);        
    }
}
