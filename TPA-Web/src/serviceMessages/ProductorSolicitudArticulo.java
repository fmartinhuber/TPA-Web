package serviceMessages;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;
import dto.SolicitudCompraDTO;

public class ProductorSolicitudArticulo extends ClienteMensajeria{


	public void sendMessage(SolicitudArticuloDTO solicitudArticuloDTO) {
		
		String host = "10.254.79.243";
		String port = "8080";
		String queueName ="jms/queue/DespachoNuevoArticulo";
		//String queueName ="jms/despacho/04/recepcionNuevoArticulo";
		String user = "test";
		String pass = "test1234";
		
		
		JsonObject jsonObject = new JsonObject();
		for (Iterator iterator = solicitudArticuloDTO.getItemsSolicitudArticulo().iterator(); iterator.hasNext();) {
			ItemSolicitudArticuloDTO itemArticuloSol = (ItemSolicitudArticuloDTO) iterator.next();
			jsonObject.addProperty("idDeposito", "G12");
			jsonObject.addProperty("codigoArticulo", itemArticuloSol.getArticulo().getCodArticulo());
			jsonObject.addProperty("cantidad", itemArticuloSol.getCantidad());
			
			try {
				this.sendMessage(jsonObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
