package serviceMessages;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: DepositoMessage
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/testQueue"), @ActivationConfigProperty(
						propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/testQueue")
public class ReceptorDepositoMessage implements MessageListener {

	/**
	 * Default constructor. 
	 */
	public ReceptorDepositoMessage() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			String messageText = null;
			if(message instanceof TextMessage){
				messageText = ((TextMessage)message).getText();
			}
			Logger.getAnonymousLogger().info("Mensaje recibido: " + messageText);
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
