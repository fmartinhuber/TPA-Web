package serviceMessages;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import controler.IDepositoControladorLocal;
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
	IDepositoControladorLocal ejb;
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
    		ObjectMessage om = (ObjectMessage) message;
    		Serializable objectData = om.getObject();
    		SolicitudCompraDTO aasdsaa = (SolicitudCompraDTO) objectData;
			if(om instanceof SolicitudCompraDTO){
				SolicitudCompraDTO solCompraDTO = (SolicitudCompraDTO) message;
				ejb.crearRecepcionCompra(solCompraDTO);
			}
			
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
		}
        
    }

}
