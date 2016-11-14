package serviceMessages;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.apache.log4j.Logger;

import dto.SolicitudCompraDTO;

@Stateless
public class ProductorSolicitudArticuloMessage {

	static Logger log = Logger.getLogger(ProductorFabricaMessage.class.getName());

	@Resource(lookup = "java:/jms/queue/solicitudArticulo")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	public void sendMessage() {
//		JMSContext c = new ActiveMQXAJMSContext(,context); 
		String json = "{\"idDespacho\":\"G05\", \"codArticulo\":101, \"cantidad\":4}";
		Message message = context.createTextMessage(json);
		context.createProducer().send(testQueue, message);
	}
}
