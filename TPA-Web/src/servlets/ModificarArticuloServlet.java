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
		
		newArticulo.setCodArticulo(Integer.valueOf(request.getParameter("codigo")));
		newArticulo.setCantidadDisponible(Integer.valueOf(request.getParameter("cantidad")));
		
//		newArticulo.setCodArticulo(583);
//		newArticulo.setCantidadDisponible(10);
		
		deposito.modificarArticulo(newArticulo);
		//deposito.modificarStockDelArticulo(newArticulo);
		
		response.getWriter().print("<h1> Se modificó el stock del artículo<h1>");				
		response.getWriter().print("<p> <a href=\"/TPA-Web-0.0.1-SNAPSHOT/\">Regresar Menu</a></p>");
		
		
		
//		ArticuloBean newArticulo = new ArticuloBean();
//		
//		newArticulo.setCodigo(Integer.valueOf(request.getParameter("codigo")));
//		//newArticulo.setCodigo(ArticuloDao.getInstancia().buscarArticuloPorCodigo(request.getParameter("codigo")).getCodigo());
//		newArticulo.setCantidadDisponible(Integer.valueOf(request.getParameter("cantidad")));
//		
//		System.out.println("codigo" +request.getParameter("codigo"));
//		System.out.println("cantidad" +request.getParameter("cantidad"));
//		
//		try {
//			
//			if(newArticulo.getCodigo() == 0){
//				response.getWriter().print("<h1>No se encontró el artículo con el código ingresado<h1>");
//				response.getWriter().print("<p> <a href=\"/tpia-rama/index.jsp\">Regresar Menu</a></p>");
//			}
//			else{
//				DepositoControlador.getInstancia().modificarStockDelArticulo(newArticulo);
//				response.getWriter().print("<h1> Se modificó el stock del artículo<h1>");				
//				response.getWriter().print("<p> <a href=\"/tpia-rama/index.jsp\">Regresar Menu</a></p>");
//			}			
//			
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
