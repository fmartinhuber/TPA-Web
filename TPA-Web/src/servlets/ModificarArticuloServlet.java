package servlets;

import java.io.IOException;
import java.rmi.NotBoundException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.*;
import dto.ArticuloDTO;

@WebServlet("/ModificarArticuloServlet")
public class ModificarArticuloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	IDepositoControladorLocal deposito;

	public ModificarArticuloServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("metodo: " + request.getMethod());
		System.out.println("metodo: " + request.getParameter("metodo"));

		try {
			if (request.getParameter("metodo").equalsIgnoreCase("modificarArticulo")) {
				modificarArticulo(request, response);
			} else if (request.getParameter("metodo").equalsIgnoreCase("nuevoMetodo")) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	private void modificarArticulo(HttpServletRequest request, HttpServletResponse response) throws NotBoundException, IOException {

		ArticuloDTO newArticulo = new ArticuloDTO();
		
		//newArticulo.setCodArticulo(Integer.valueOf(request.getParameter("codigo")));
		newArticulo.setCodArticulo(request.getParameter("codigo"));
		newArticulo.setCantidadDisponible(Integer.valueOf(request.getParameter("cantidad")));
		
		String miCodigo = request.getParameter("codigo");
		
		newArticulo.setNombre(deposito.obtenerArticuloPorCodigo(miCodigo).getNombre());
		newArticulo.setDescripcion(deposito.obtenerArticuloPorCodigo(miCodigo).getDescripcion());
		newArticulo.setColor(deposito.obtenerArticuloPorCodigo(miCodigo).getColor());
		newArticulo.setMarca(deposito.obtenerArticuloPorCodigo(miCodigo).getMarca());
		newArticulo.setPrecio(deposito.obtenerArticuloPorCodigo(miCodigo).getPrecio());
		newArticulo.setFoto(deposito.obtenerArticuloPorCodigo(miCodigo).getFoto());
		newArticulo.setOrigen(deposito.obtenerArticuloPorCodigo(miCodigo).getOrigen());
		newArticulo.setTipo(deposito.obtenerArticuloPorCodigo(miCodigo).getTipo());
		newArticulo.setEdadRecomendada(deposito.obtenerArticuloPorCodigo(miCodigo).getEdadRecomendada());
		newArticulo.setMaterial(deposito.obtenerArticuloPorCodigo(miCodigo).getMaterial());
		newArticulo.setTalle(deposito.obtenerArticuloPorCodigo(miCodigo).getTalle());
		newArticulo.setFichaTecnica(deposito.obtenerArticuloPorCodigo(miCodigo).getFichaTecnica());
		newArticulo.setIdDeposito(deposito.obtenerArticuloPorCodigo(miCodigo).getIdDeposito());
		newArticulo.setFecha(deposito.obtenerArticuloPorCodigo(miCodigo).getFecha());
				
		if( miCodigo == null ){
//			response.getWriter().print("<h1>El código ingresado no existe<h1>");				
//			response.getWriter().print("<p> <a href=\"/TPA-Web-0.0.1-SNAPSHOT/\">Regresar Menu</a></p>");
		}
		else{
			deposito.modificarStockDelArticulo(newArticulo);
			
//			response.getWriter().print("<h1> Se modificó el stock del artículo<h1>");				
//			response.getWriter().print("<p> <a href=\"/TPA-Web-0.0.1-SNAPSHOT/\">Regresar Menu</a></p>");
		}							
	}

}
