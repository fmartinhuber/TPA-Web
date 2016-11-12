package serviceMessages;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import controler.IDepositoControladorLocal;
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
	IDepositoControladorLocal deposito;
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
				ObjectMapper mapper = new ObjectMapper();
				SolicitudArticuloDTO solicitud = mapper.readValue(messageText, SolicitudArticuloDTO.class);
				deposito.crearSolicitudArticulo(solicitud);
			}
			Logger.getAnonymousLogger().info("Mensaje recibido: " + messageText);
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
		}
        
    }

}
