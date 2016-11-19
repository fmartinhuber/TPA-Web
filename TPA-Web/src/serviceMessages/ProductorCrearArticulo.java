package serviceMessages;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.google.gson.JsonObject;

import dto.ArticuloDTO;

public class ProductorCrearArticulo extends ClienteMensajeria{

	private String host;
	private String port;
	private String queueName;
	private String user;
	private String pass;
	
	public void sendMessageLogistica(ArticuloDTO articuloDto) {
		
		host = "192.168.0.105";
		port = "8080";
		queueName ="jms/queue/eventoAuditoria";
		user = "guest";
		pass = "guest";
		
		
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
		jsonObject.addProperty("descripcion", "Se dio de alta el Articulo " + articuloDto.getCodArticulo() + " Desc: " + articuloDto.getDescripcion());
		System.out.println(jsonObject.toString());
		try {
			this.sendMessage(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void sendMessageDespacho(ArticuloDTO articuloDTO) {
		host = "192.168.0.104";
		port = "8080";
		queueName ="jms/queue/DespachoNuevoArticulo";
		user = "test";
		pass = "test1234";
		
		this.setProviderUrl(host +":"+port);
		this.setUserName(user);
		this.setPassword(pass);
		this.setQueueName(queueName);
		
		JsonObject jsonObject = new JsonObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = sdf.format(new Date());
		jsonObject.addProperty("idDeposito", "G12");
		jsonObject.addProperty("codArticulo", articuloDTO.getCodArticulo());
		jsonObject.addProperty("nombre", articuloDTO.getNombre());	
		jsonObject.addProperty("descripcion", articuloDTO.getDescripcion());
		jsonObject.addProperty("marca", articuloDTO.getMarca());
		jsonObject.addProperty("precio", articuloDTO.getPrecio());
		jsonObject.addProperty("foto", articuloDTO.getFoto());
		jsonObject.addProperty("origen", articuloDTO.getOrigen());
		jsonObject.addProperty("tipo", articuloDTO.getTipo());
		jsonObject.addProperty("datosExtra", articuloDTO.getTipo());
		
		System.out.println(jsonObject.toString());
		try {
			this.sendMessage(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessagePortal(ArticuloDTO articuloDTO) {
		
		host = "192.168.0.103";
		port = "8080";
		queueName ="jms/queue/deposito";
		user = "guest";
		pass = "guest";
		
		this.setProviderUrl(host +":"+port);
		this.setUserName(user);
		this.setPassword(pass);
		this.setQueueName(queueName);
		
		JsonObject jsonObject = new JsonObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = sdf.format(new Date());
		jsonObject.addProperty("idDeposito", "G12");
		jsonObject.addProperty("codArticulo", articuloDTO.getCodArticulo());
		jsonObject.addProperty("nombre", articuloDTO.getNombre());	
		jsonObject.addProperty("descripcion", articuloDTO.getDescripcion());
		jsonObject.addProperty("marca", articuloDTO.getMarca());
		jsonObject.addProperty("precio", articuloDTO.getPrecio());
		jsonObject.addProperty("foto", articuloDTO.getFoto());
		jsonObject.addProperty("origen", articuloDTO.getOrigen());
		jsonObject.addProperty("tipo", articuloDTO.getTipo());
//		jsonObject.addProperty("datosExtra", articuloDTO.get);
		String asd = "{\"idDeposito\":\"G12\",\"codArticulo\":"+ articuloDTO.getCodArticulo() +",\"nombre\":\"" + articuloDTO.getNombre() + "\",\"descripcion\": \"" + articuloDTO.getDescripcion() +"\",\"marca\": \"" + articuloDTO.getMarca() + "\",\"precio\": " + articuloDTO.getPrecio() + ",\"foto\": \"" + articuloDTO.getFoto() +"\",\"origen\": \"" + articuloDTO.getOrigen() +"\",\"tipo\": \"" + articuloDTO.getTipo() + "\",\"datosExtra\":{\"fichaTecnica\":\"una ficha tecnica en texto libre\"}}";
		System.out.println(asd);
		try {
			this.sendMessage(asd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
