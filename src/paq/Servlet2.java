package paq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.parser.Cookie;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url;
			// Se leen los parámetros
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String email = request.getParameter("email");
			// Se crea el objeto usuario (se supone que existe la clase Usuario)
			Usuario usuario = new Usuario(nombre, apellidos, email);


			System.out.println(request.getSession(false)==null);
			if (request.getSession(false)==null){
				if(request.getParameter("nombre")==null) {
					url="/WEB-INF/accesoNulo.html";
					getServletContext().getRequestDispatcher(url).forward(request,response);
				}else {
					System.out.println("Sesion no activa");
					nombre=request.getParameter("nombre");
					apellidos=request.getParameter("apellidos");
				    email=request.getParameter("email");
					request.setAttribute("nombre", nombre);
					request.setAttribute("apellidos",apellidos);
					request.setAttribute("email",email);
					HttpSession sesion=request.getSession(true);
					System.out.println("Sesion activada");
					sesion.setMaxInactiveInterval(60);
					sesion.setAttribute("nombre",nombre);
					sesion.setAttribute("apellidos",apellidos);
					sesion.setAttribute("email",email);
					response.setContentType("text/html");
					url="/WEB-INF/informacionAcceso.jsp";
					getServletContext().getRequestDispatcher(url).forward(request,response);
					System.out.println(nombre + " " + apellidos + " "+ email);
				}

			}else {
				
				HttpSession sesion=request.getSession();
				System.out.println("Leemos datos de sesion");
				nombre=(String)sesion.getAttribute("nombre");
				apellidos=(String)sesion.getAttribute("apellidos");
				email=(String)sesion.getAttribute("email");
				request.setAttribute("nombre", nombre);
				request.setAttribute("apellidos",apellidos);
				request.setAttribute("email",email);
				url="/WEB-INF/informacionAcceso.jsp";
				getServletContext().getRequestDispatcher(url).forward(request,response);
				System.out.println(nombre + " " + apellidos + " "+ email);
			} 
			
			
		}
	}


