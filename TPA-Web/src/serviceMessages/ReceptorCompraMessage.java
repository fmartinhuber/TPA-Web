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
import dto.SolicitudCompraDTO;

/**
 * Message-Driven Bean implementation class for: ReceptorCompraMessage
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/recepcionCompra"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/recepcionCompra")
public class ReceptorCompraMessage implements MessageListener {

	@EJB
	DepositoControlador ejb;
    /**
     * Default constructor. 
     */
    public ReceptorCompraMessage() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
			if(message instanceof SolicitudCompraDTO){
				SolicitudCompraDTO solCompraDTO = (SolicitudCompraDTO) message;
				ejb.crearRecepcionCompra(solCompraDTO);
			}
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
		}
        
    }

}
