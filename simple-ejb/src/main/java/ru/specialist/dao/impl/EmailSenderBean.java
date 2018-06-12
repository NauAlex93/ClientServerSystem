package ru.specialist.dao.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import ru.specialist.IllegalEmailException;

@Stateless(name="EmailSenderBean", mappedName="EmailSenderBean")
@LocalBean
public class EmailSenderBean {
    
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    @Resource(mappedName="jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "jms/MyQueue")
    private Queue queue;
    
    public void sendEmail(String userEmail) throws IllegalEmailException {
        System.out.println("try send message for [" + userEmail + "]");
        if (!isValidEmail(userEmail)) {
            throw new IllegalEmailException(userEmail);
        }
        try (Connection connection = connectionFactory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText(userEmail);
            producer.send(message);
            session.close();           
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
    
    private boolean isValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
    
    
}
