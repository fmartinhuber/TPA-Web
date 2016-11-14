//package serviceMessages;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//

//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.EJB;
//import javax.ejb.MessageDriven;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//import controler.IDepositoControladorLocal;
//import dto.ArticuloDTO;
//import dto.ItemSolicitudArticuloDTO;
//import dto.SolicitudArticuloDTO;
//
///**
// * Message-Driven Bean implementation class for: ReceptorSolicitudArticulo
// */
//@MessageDriven(
//		activationConfig = { @ActivationConfigProperty(
//				propertyName = "destination", propertyValue = "java:/jms/queue/solicitudArticulo"), @ActivationConfigProperty(
//				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
//		}, 
//		mappedName = "java:/jms/queue/solicitudArticulo")
//public class ReceptorSolicitudArticuloMessage implements MessageListener {
//
//	@EJB
//	IDepositoControladorLocal deposito;
//    /**
//     * Default constructor. 
//     */
//    public ReceptorSolicitudArticuloMessage() {
//        // TODO Auto-generated constructor stub
//    }
//	
//	/**
//     * @see MessageListener#onMessage(Message)
//     */
//    @SuppressWarnings("deprecation")
//	public void onMessage(Message message) {
//    	try {
//			String messageText = null;
//			if(message instanceof TextMessage){
//				messageText = ((TextMessage)message).getText();
//				System.out.println(messageText);
//				JsonObject json = new Gson().fromJson(messageText, JsonObject.class);
//				
//				
//				ArticuloDTO articulo = deposito.obtenerArticuloPorCodigo(json.get("codArticulo").toString());
//				if(articulo != null){
//					SolicitudArticuloDTO solicitud = new SolicitudArticuloDTO();
//					solicitud.setCodigo(articulo.getCodArticulo());
//					solicitud.setEstado("Pendiente");
//					solicitud.setFechaEntrega(new Date(2016,12,31));
//					solicitud.setIdModulo(json.get("idDespacho").toString());
//					ItemSolicitudArticuloDTO itemSolArt = new ItemSolicitudArticuloDTO(articulo,Integer.parseInt(json.get("cantidad").toString()));
//					List<ItemSolicitudArticuloDTO> listaArt = new ArrayList<ItemSolicitudArticuloDTO>();
//					listaArt.add(itemSolArt);
//					solicitud.setItemsSolicitudArticulo(listaArt);
//					deposito.crearSolicitudArticulo(solicitud);
//				}else{
//					System.out.println("MANDE UN ARTICULO COMO LA GENTE !");
//				}
//			}
//			Logger.getAnonymousLogger().info("Mensaje recibido: " + messageText);
//		} catch (Exception e) {
//			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
//		}
//        
//    }
//
//}
