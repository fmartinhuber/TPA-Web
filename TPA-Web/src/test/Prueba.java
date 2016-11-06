package test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import interfaz.ArticuloEJBLocal;

public class Prueba {

	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub

		// Aqui hay que llamar al metodo!!
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.niming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "TPIA_EAR";
		final String ejbModuleName = "TPA-Server";
		final String distanctName = "";
		final String ejbClassName = "ArticuloEJB";
		final String fullInterfaceName = ArticuloEJBLocal.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distanctName + "/" + ejbClassName + "!" + fullInterfaceName;
		System.out.println("Conectado a: " + lookupName);
		ArticuloEJBLocal mbr = (ArticuloEJBLocal) context.lookup(lookupName);
		System.out.println("Respuesta: " + mbr.listarArticulos());
		
		
	}

}
