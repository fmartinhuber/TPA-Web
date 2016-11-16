package serviceMessages;

import javax.jms.Message;

public class MockProductoSolicitudArticulo extends ClienteMensajeria{

	
	public void sendMessage() {
		
		String host = "10.254.79.5";
		String port = "8080";
		String queueName ="jms/queue/solicitudArticulo2";
		String user = "test";
		String pass = "test1234";
		
		
		this.setProviderUrl(host +":"+port);
		this.setUserName(user);
		this.setPassword(pass);
		this.setQueueName(queueName);
		
		String json = "{\"idDespacho\":\"G05\", \"codArticulo\":101, \"cantidad\":4}";
		try {
			this.sendMessage(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
