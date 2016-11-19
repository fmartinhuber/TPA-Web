package serviceREST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;

import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;

public class SolicitudArticuloClient {

	public static void conexion(SolicitudArticuloDTO solicitudArticuloDTO) {

		try {
			
			String DespachoXX = "http://192.168.0.104:8080/DespachoJMS/api/json/solicitudArticulo";
			for (Iterator iterator = solicitudArticuloDTO.getItemsSolicitudArticulo().iterator(); iterator.hasNext();) {
				//Daro 17.11: Definir las URL de Despacho
				String DespachoYY = "acaURL";
				String DespachoZZ = "acaURL";
				
				//Obtener el Despacho que se comunica con nosotros
				String obtenerDespacho = solicitudArticuloDTO.getIdDespacho();
				
				//Dependiendo el despacho, llamo a esa URL
				URL url = null;
				if (obtenerDespacho.equals("\"G11\"")){
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
					jsonObject.addProperty("codArticulo", Integer.valueOf(itemArticuloSol.getArticulo().getCodArticulo()));
					jsonObject.addProperty("cantidad", itemArticuloSol.getCantidad());
					String JSON = jsonObject.toString();
					System.out.println("JSON: " + JSON);
					
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
//					connection.setRequestMethod("POST");
					
					OutputStream out = connection.getOutputStream();
					out.write(JSON.toString().getBytes());
					out.flush();
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String response = IOUtils.toString(connection.getInputStream());
					while (in.readLine() != null) {
						System.out.println("\nSolicitudArticuloClient Invoked Successfully..");
					}
					out.close();
					System.out.println(response);
				}
			}
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}