package servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ArticuloDTO;
//import bean.ArticuloBean;
import interfaz.ArticuloEJBLocal;
import negocio.ArticuloEJB;

/**
 * Servlet implementation class PruebaServlet
 */
@WebServlet("/PruebaServlet")
public class PruebaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	ArticuloEJB ejb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		ArticuloEJBLocal.
//		try {
//			InitialContext ic = new InitialContext();
//			ArticuloBean asd = (ArticuloBean) 
//			ic.lookup("java:global/TPA-ServerEAR/TPA-Server/ArticuloEJB!negocio.ArticuloEJB");
//			System.out.println(asd.toString());
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List asd = ejb.listarArticulos();
		System.out.println(asd.toString());
		for (Iterator iterator = asd.iterator(); iterator.hasNext();) {
			ArticuloDTO object = (ArticuloDTO) iterator.next();
			response.getWriter().append(object.toString());
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
