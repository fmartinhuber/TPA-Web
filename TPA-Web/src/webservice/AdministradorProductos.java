package webservice;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class AdministradorProductos {
	@WebMethod
	public Producto obtenerProducto(int id) {
		Producto producto = new Producto();
		producto.setId(id);
		producto.setDescripcion("Lavarropas");
		return producto;
	}
}
