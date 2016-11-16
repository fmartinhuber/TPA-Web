package serviceMessages;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.google.gson.JsonObject;

import dto.ArticuloDTO;

public class ProductorCrearArticulo extends ClienteMensajeria{

	public void sendMessage(ArticuloDTO articuloDto) {
		
		String host = "10.254.79.68";
		String port = "8080";
		String queueName ="jms/queue/eventoAuditoria";
		String user = "guest";
		String pass = "guest";
		
		
		this.setProviderUrl(host +":"+port);
		this.setUserName(user);
		this.setPassword(pass);
		this.setQueueName(queueName);
		
		JsonObject jsonObject = new JsonObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = sdf.format(new Date());
		LocalDateTime dateTime = LocalDateTime.parse("2016-03-23T18:21");
		jsonObject.addProperty("fecha", date);
		jsonObject.addProperty("tipo", "Deposito");
		jsonObject.addProperty("modulo", "G12");
		jsonObject.addProperty("descripcion", "Se dio de alta el Articulo " + articuloDto.getCodArticulo());
		
		try {
			this.sendMessage(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
