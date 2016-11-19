package serviceMessages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.JsonObject;

import controler.IDepositoControladorRemote;

/**
 * Message-Driven Bean implementation class for: ReceptorSolicitudArticulo
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "jms/queue/solicitudArticulo2"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/queue/solicitudArticulo2")
public class ReceptorSolicitudArticulo implements MessageListener {

	@EJB
	IDepositoControladorRemote deposito;
    /**
     * Default constructor. 
     */
    public ReceptorSolicitudArticulo() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	System.out.println("ALGO LLEGO");
    	if(message instanceof TextMessage) {
    		TextMessage textMessage = (TextMessage) message;
			try {
				deposito.crearSolicitudArticulo(textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
    	}
	}

}
