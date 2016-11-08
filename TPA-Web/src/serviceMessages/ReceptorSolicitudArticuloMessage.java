package serviceMessages;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import controler.DepositoControlador;
import dto.SolicitudArticuloDTO;

/**
 * Message-Driven Bean implementation class for: ReceptorSolicitudArticulo
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/solicitudArticulo"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/solicitudArticulo")
public class ReceptorSolicitudArticuloMessage implements MessageListener {

	@EJB
	DepositoControlador deposito;
    /**
     * Default constructor. 
     */
    public ReceptorSolicitudArticuloMessage() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
			String messageText = null;
			if(message instanceof TextMessage){
				messageText = ((TextMessage)message).getText();
				SolicitudArticuloDTO asd = null;
				//ToDo: Convertir a JSON con el formato solicitudarticuloDTO
				deposito.crearSolicitudArticulo(asd);
			}
			Logger.getAnonymousLogger().info("Mensaje recibido: " + messageText);
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
		}
        
    }

}
