import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet2() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {

			String nombre = "No registrado";
			String edad = "No registrada";
			String email = "No registrado";
			String genero = "No registrado";
			String esEstudiante = "No registrado";

		
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("uname")) {
						nombre = URLDecoder.decode(c.getValue(), "UTF-8");
					} else if (c.getName().equals("uage")) {
						edad = c.getValue();
					} else if (c.getName().equals("uemail")) {
						email = URLDecoder.decode(c.getValue(), "UTF-8");
					} else if (c.getName().equals("ugender")) {
						genero = c.getValue();
					} else if (c.getName().equals("ustudent")) {
						esEstudiante = c.getValue();
					}
				}
			}

		
			out.print("<h2>Datos leídos desde las Cookies en Servlet2:</h2>");
			out.print("<ul>");
			out.print("<li><b>Nombre:</b> " + nombre + "</li>");
			out.print("<li><b>Edad:</b> " + edad + "</li>");
			out.print("<li><b>Correo:</b> " + email + "</li>");
			out.print("<li><b>Género:</b> " + genero + "</li>");
			out.print("<li><b>¿Es Estudiante?:</b> " + esEstudiante + "</li>");
			out.print("</ul>");

			out.print("<br><a href='index.html'>Volver al inicio</a>");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}

