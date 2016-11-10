package serviceMessages;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;

import dto.SolicitudArticuloDTO;

@Stateless
public class ProductorFabricaMessage {


//	@Resource(lookup = "java:/jms/queue/solicitudArticuloProductor")
	@Resource(lookup = "java:/jms/queue/recepcionCompra")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	public void sendMessage(SolicitudArticuloDTO solicitudArticulo) {
//		JMSContext c = new ActiveMQXAJMSContext(,context); 
		ObjectMessage  message = context.createObjectMessage(solicitudArticulo);
		context.createProducer().send(testQueue, message);
	}

}