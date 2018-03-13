package paq;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String nombreUsuario = "jnl00005";
	private static final String claveUsuario = "practica1-3";

    /**
     * Default constructor. 
     */
    public Servlet1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			ArrayList<Usuario> LUsuarios = new ArrayList<Usuario>();
			Usuario usuario1 = new Usuario("Pedro Javier", "Saez Mira", "pjsm0003@red.ujaen.es");
			Usuario usuario2 = new Usuario("Juan", "Nuñez Lerma", "jnl00005@red.ujaen.es");
			LUsuarios.add(usuario1);
			LUsuarios.add(usuario2);
			request.setAttribute("LUsuarios", LUsuarios);//Pasamos la lista usuarios al objeto request para que pueda interactuar con el jsp.
			
			// Se leen los parámetros
			String usuario = request.getParameter("usuario");
			String clave = request.getParameter("clave");
			String url="";

			if (usuario.equals(nombreUsuario) && clave.equals(claveUsuario)) {
						url = "/WEB-INF/usuarios.jsp";
			}
			else
			{
				url = "/WEB-INF/registro.html";
			}
					
			getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
