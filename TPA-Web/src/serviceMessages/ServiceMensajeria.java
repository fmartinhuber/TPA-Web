package serviceMessages;

public class ServiceMensajeria {

	private ClientMensajeria client;
	
	
	
	public ServiceMensajeria() {
		this.client = new ClientMensajeria();
		
		String host = "192.168.0.21";
		String port = "8080";
		String queueName ="jms/queue/solicitudArticuloProductor";
		//String queueName ="jms/despacho/04/recepcionNuevoArticulo";
		String user = "test";
		String pass = "test1234";
		
		client.setProviderUrl(host + ":" + port);
		client.setQueueName(queueName);
		
//		if(shouldUseSecurity == true){
//			client.setUserName(user);
//			client.setPassword(pass);
//		}
		
		client.setUserName(user);
		client.setPassword(pass);
		try {
			client.sendMessage("Mensaje de prueba");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
