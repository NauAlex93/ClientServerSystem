package ru.specialist.msg;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import ru.specialist.dao.UserDAOLocal;
import ru.specialist.entity.User;

@MessageDriven(
    mappedName="jms/MyQueue",
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/MyQueue")    
    }
)
public class MessageBean implements MessageListener {
    
    @EJB
    private UserDAOLocal userDAO;
    
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                String userEmail = msg.getText();
                System.out.println("MessageBean recived user email [" + userEmail + "]");
                User user = userDAO.find(userEmail);
                System.out.println("user [" + user + "]");
                //отправка сообщения по электронной почте
                sendEmail(user);
            } 
        } catch (Exception e) {
            throw new EJBException(e.toString());
        }
    }
    
    private void sendEmail(User user) {
        //TODO отправка сообщения по электронной почте
    }
    
}
