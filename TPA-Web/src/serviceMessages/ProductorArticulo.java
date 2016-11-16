package serviceMessages;

public class ProductorArticulo extends ClienteMensajeria{

	private ClienteMensajeria client;
	
	public  ProductorArticulo() {
		this.client = new ClienteMensajeria();
		
		String host = "10.254.79.243";
		String port = "8080";
		String queueName ="jms/queue/DespachoNuevoArticulo";
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
