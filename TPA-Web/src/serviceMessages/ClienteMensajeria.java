package serviceMessages;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClienteMensajeria {

	// Set up all the default values
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String TEST_QUEUE_DESTINATION = "jms/queue/recepcion_fabrica_queue";

	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";

	private static final String DEFAULT_PROVIDER_URL = "remote://localhost:4447";
	private static final String DEFAULT_USERNAME = "test";
	private static final String DEFAULT_PASSWORD = "test1234.";

	private String providerUrl;
	private String userName;
	private String password;
	private String queueName;

	public String getProviderUrl() {
		if (providerUrl == null || providerUrl == "") {
			providerUrl = DEFAULT_PROVIDER_URL;
		}
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = "http-remoting://" + providerUrl;
	}

	public String getUserName() {
		if (userName == null || userName == "") {
			userName = DEFAULT_USERNAME;
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		if (password == null || password == "") {
			password = DEFAULT_PASSWORD;
		}
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQueueName() {
		if (queueName == null || queueName == "") {
			queueName = TEST_QUEUE_DESTINATION;
		}
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	private Properties buildJndiProperties() {
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		jndiProps.put(Context.PROVIDER_URL, this.getProviderUrl());
		jndiProps.put(Context.SECURITY_PRINCIPAL, this.getUserName());
		jndiProps.put(Context.SECURITY_CREDENTIALS, this.getPassword());
		System.out.println(">>>>>>>>getProviderUrl: " + getProviderUrl());
		System.out.println(">>>>>>>>getUserName: " + getUserName());
		System.out.println(">>>>>>>>getPassword: " + getPassword());
		return jndiProps;
	}

	private Connection getConnection(Context context) throws NamingException, JMSException {
		String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
		Connection connection = connectionFactory.createConnection(System.getProperty("username", this.getUserName()),
				System.getProperty("password", this.getPassword()));

		return connection;

	}

	public void sendMessage(String message) throws Exception {
		System.out.println(">>>>>>>>>>getQueueName: " + getQueueName());
		Context context = new InitialContext(buildJndiProperties());
		Connection connection = getConnection(context);

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		String destinationString = System.getProperty("destination", this.getQueueName());
		Destination destination = (Destination) context.lookup(destinationString);
		MessageProducer producer = session.createProducer(destination);
		connection.start();
		
		TextMessage msg = session.createTextMessage();
		msg.setText(message);

		producer.send(msg);
		System.out.println("Se envio el mensaje: " + msg);
		connection.close();

	}

}
