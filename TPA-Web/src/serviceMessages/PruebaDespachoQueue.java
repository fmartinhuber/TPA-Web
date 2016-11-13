package serviceMessages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

@Stateless
public class PruebaDespachoQueue {
	
	public final static String JMS_CONNECTION_FACTORY_JNDI = "java:jboss/exported/jms/RemoteConnectionFactory";
	public final static String JMS_QUEUE_JNDI = "jms/queue/DespachoNuevoArticulo";
	public final static String JMS_USERNAME = "test"; // The role for
																// this user is
																// "guest" in
																// ApplicationRealm
	public final static String JMS_PASSWORD = "test1234";
	public final static String WILDFLY_REMOTING_URL = "http-remoting://192.168.1.235:8080";

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage msg;

//	public static void main(String[] args) throws Exception {
//		InitialContext ic = getInitialContext();
//		WildFlyJmsQueueSender queueSender = new WildFlyJmsQueueSender();
//		queueSender.init(ic, JMS_QUEUE_JNDI);
//		readAndSend(queueSender);
//		queueSender.close();
//	}

	@SuppressWarnings("static-access")
	public void init(Context ctx, String queueName) throws NamingException, JMSException {
		qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_CONNECTION_FACTORY_JNDI);
		qcon = qconFactory.createQueueConnection(this.JMS_USERNAME, this.JMS_PASSWORD);
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);
		qsender = qsession.createSender(queue);
		msg = qsession.createTextMessage();
		qcon.start();
	}

	public void send(String message) throws JMSException {
		msg.setText(message);
		qsender.send(msg);
	}

	public void close() throws JMSException {
		qsender.close();
		qsession.close();
		qcon.close();
	}

	public static void prueba(String mensaje){
		try {
			InitialContext ic = getInitialContext();
			WildFlyJmsQueueSender queueSender = new WildFlyJmsQueueSender();
			queueSender.init(ic, JMS_QUEUE_JNDI);
//			readAndSend(queueSender);
			queueSender.send(mensaje);
			queueSender.close();
		} catch (JMSException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void readAndSend(WildFlyJmsQueueSender wildFlyJmsQueueSender)
			throws IOException, JMSException, InterruptedException {
		String line1 = "{\"idDeposito\":\"G01\",\"codArticulo\":1234,\"nombre\":\"Radio Electrico\",\"descripcion\":\"Radio Electrico 700w\",\"marca\":\"Phillips\",\"precio\":1500.0,\"foto\":\"http://imgur.com/123456\",\"origen\":\"China\",\"tipo\":\"Electro\",\"datosExtra\":{\"fichaTecnica\":\"una ficha tecnica en texto libre\"}}";
		String line2 = "{\"idDeposito\":\"G01\",\"codArticulo\":2234,\"nombre\":\"Auto Stereo\",\"descripcion\":\"Stereo de Auto Peugeot 504 Original\",\"marca\":\"Philco\",\"precio\":500.0,\"foto\":\"http://www.dhresource.com/0x0s/f2-albu-g3-M01-B6-D4-rBVaHVWbUeGAP0cQAANMVYBrXsw752.jpg/car-auto-radio-remote-control-car-mp3-player.jpg\",\"origen\":\"Taiwan\",\"tipo\":\"Electro\",\"datosExtra\":{\"fichaTecnica\":\"Funciona bien!!!!\"}}";
		String line3 = "{\"idDeposito\":\"G02\",\"codArticulo\":3333,\"nombre\":\"Parlantes Stereo 200w RMS\",\"descripcion\":\"Juegos de dos parlantes de 200W RMS\",\"marca\":\"Noga\",\"precio\":250.0,\"foto\":\"https://http2.mlstatic.com/parlantes-potenciados-genius-sp-hf800a-3-vias-20w-800a-D_NQ_NP_13101-MLA20072033168_032014-F.jpg\",\"origen\":\"Korea\",\"tipo\":\"Electro\",\"datosExtra\":{\"fichaTecnica\":\"Suenan a la perfección\"}}";
		String line4 = "{\"idDeposito\":\"G02\",\"codArticulo\":4321,\"nombre\":\"Mesa de Luz\",\"descripcion\":\"Mesa de luz de madera con 1 cajón \",\"marca\":\"Phillips\",\"precio\":3000.0,\"foto\":\"http://falabella.scene7.com/is/image/FalabellaAR/1805624_1?$producto308$&iv=OJKnB0&wid=924&hei=924&fit=fit,1\",\"origen\":\"Argentina\",\"tipo\":\"Mueble\",\"datosExtra\":{\"fichaTecnica\":\"Bla bla bla...\"}}";
		String line5 = "{\"idDeposito\":\"G03\",\"codArticulo\":1237,\"nombre\":\"Auto a Control Remoto\",\"descripcion\":\"Auto a control remoto muy bonito\",\"marca\":\"Juguetes\",\"precio\":1999.9,\"foto\":\"http://i756.photobucket.com/albums/xx205/HOBBYMANIASTORE/AUTOMODELISMO/SLASH-4X4-en-Hobbymania-Hobby-Shop-Foto1.png\",\"origen\":\"China\",\"tipo\":\"Infantil\",\"datosExtra\":{\"fichaTecnica\":\"una ficha tecnica en texto libre\"}}";
		wildFlyJmsQueueSender.send(line1);
		System.out.println("JMS Message Sent: " + line1 + "\n");
		Thread.sleep((long) (Math.random() * 1000) + 5000);
		wildFlyJmsQueueSender.send(line2);
		System.out.println("JMS Message Sent: " + line2 + "\n");
		Thread.sleep((long) (Math.random() * 1000) + 5000);
		wildFlyJmsQueueSender.send(line3);
		System.out.println("JMS Message Sent: " + line3 + "\n");
		Thread.sleep((long) (Math.random() * 1000) + 5000);
		wildFlyJmsQueueSender.send(line4);
		System.out.println("JMS Message Sent: " + line4 + "\n");
		Thread.sleep((long) (Math.random() * 1000) + 5000);
		wildFlyJmsQueueSender.send(line5);
		System.out.println("JMS Message Sent: " + line5 + "\n");
	}

	private static InitialContext getInitialContext() throws NamingException {
		InitialContext context = null;
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL); // NOTICE:
																	// "http-remoting"
																	// and port
																	// "8080"
			props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
			props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
			context = new InitialContext(props);
			System.out.println("\n\tGot initial Context: " + context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context;
	}
}