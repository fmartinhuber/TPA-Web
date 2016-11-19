package serviceREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import com.google.gson.JsonObject;

import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;

public class SolicitudArticuloClient {

	public static void conexion(SolicitudArticuloDTO solicitudArticuloDTO) {

		try {
			
			String DespachoXX = "http://172.16.164.51:8080/DespachoJMS/apis/json/solicitudArticulo";
			for (Iterator iterator = solicitudArticuloDTO.getItemsSolicitudArticulo().iterator(); iterator.hasNext();) {
				//Daro 17.11: Definir las URL de Despacho
				String DespachoYY = "acaURL";
				String DespachoZZ = "acaURL";
				
				//Obtener el Despacho que se comunica con nosotros
				String obtenerDespacho = solicitudArticuloDTO.getIdDespacho();
				
				//Dependiendo el despacho, llamo a esa URL
				URL url = null;
				if (obtenerDespacho.equals("D11")){
					url = new URL (DespachoXX);
				}
				if (obtenerDespacho.equals("DYY")){
					url = new URL (DespachoYY);
				}
				if (obtenerDespacho.equals("DZZ")){
					url = new URL (DespachoZZ);
				}
				
				if(url != null){
					
					ItemSolicitudArticuloDTO itemArticuloSol = (ItemSolicitudArticuloDTO) iterator.next();
					JsonObject jsonObject = new JsonObject();
					
					jsonObject.addProperty("idDeposito", "G12");
					jsonObject.addProperty("codigoArticulo", itemArticuloSol.getArticulo().getCodArticulo());
					jsonObject.addProperty("cantidad", itemArticuloSol.getCantidad());
					
					URLConnection connection = url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					String JSON = jsonObject.toString();
					out.write(JSON.toString());
					out.close();
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					
					while (in.readLine() != null) {
						System.out.println("\nSolicitudArticuloClient Invoked Successfully..");
					}
				}
			}
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}