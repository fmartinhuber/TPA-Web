package serviceMessages;

import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.utils.json.JSONObject;

import controler.IDepositoControladorLocal;
import dto.ArticuloDTO;
import dto.ItemSolicitudArticuloDTO;
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
    @SuppressWarnings("deprecation")
	public void onMessage(Message message) {
    	try {
			String messageText = null;
			if(message instanceof TextMessage){
				messageText = ((TextMessage)message).getText();
				JSONObject json = new JSONObject();
				
				String despacho = json.getString("idDespacho");
				ArticuloDTO articulo = deposito.obtenerArticuloPorCodigo(json.getString("codArticulo"));
				SolicitudArticuloDTO solicitud = new SolicitudArticuloDTO();
				solicitud.setCodigo(articulo.getCodArticulo());
				solicitud.setEstado("Pendiente");
				solicitud.setFechaEntrega(new Date(2016,12,31));
				//solicitud.setIdDespacho(json.getString("idDespacho"));
				ItemSolicitudArticuloDTO itemSolArt = new ItemSolicitudArticuloDTO(articulo,Integer.parseInt(json.getString("cantidad")));
								  											
				deposito.crearSolicitudArticulo(solicitud);
			}
			Logger.getAnonymousLogger().info("Mensaje recibido: " + messageText);
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
		}
        
    }

}
