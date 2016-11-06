package serviceMessages;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQXAJMSContext;


@Stateless
public class ProductorDepositoMessage {


	@Resource(lookup = "java:/jms/queue/testQueue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
//	@JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
	JMSContext context;

	public void sendMessage(String messageText) {
//		JMSContext c = new ActiveMQXAJMSContext(,context); 
		TextMessage message = context.createTextMessage(messageText);
		context.createProducer().send(testQueue, message);
	}

}
