import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet1() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();

			String Nombre = request.getParameter("userName");
			String edadStr = request.getParameter("userAge");
			String email = request.getParameter("email");
			String genero = request.getParameter("userGender");
			String esEstudianteStr = request.getParameter("isStudent");

			boolean esValido = true;
			StringBuilder errores = new StringBuilder();

			
			if (Nombre == null || Nombre.trim().isEmpty()) {
				esValido = false;
				errores.append("<li>Te falta rellenar el nombre.</li>");}
				 else if (Nombre.length() < 3 || Nombre.length() > 49) {
						errores.append("El nombre debe tener entre 3 y 49 caracteres.");
					} 
			 else if (!Nombre.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
				esValido = false;
				errores.append("<li>El nombre solo debe contener letras.</li>");
			}

			int edad = 0;
			if (edadStr == null || edadStr.trim().isEmpty()) {
				esValido = false;
				errores.append("<li>Te falta rellenar la edad.</li>");
			} else {
				try {
					edad = Integer.parseInt(edadStr.trim());
					if (edad < 1 || edad > 110) {
						esValido = false;
						errores.append("<li>La edad debe estar entre 1 y 110.</li>");
					}
				} catch (Exception e) {
					esValido = false;
					errores.append("<li>La edad debe ser un número entero.</li>");
				}
			}

			if (email == null || email.trim().isEmpty()) {
				esValido = false;
				errores.append("<li>Te falta rellenar el correo.</li>");
			}

			if (genero == null || genero.trim().isEmpty()) {
				esValido = false;
				errores.append("<li>Debe seleccionar un género.</li>");
			}

			String esEstudiante = (esEstudianteStr != null && esEstudianteStr.equals("Si")) ? "Si" : "No";

			if (!esValido) {
				out.print("<h2>Errores en el formulario:</h2>");
				out.print("<ul style='color:red;'>" + errores.toString() + "</ul>");
				out.print("<a href='index.html'>Volver al formulario</a>");
			} else {
				
				Cookie ck = new Cookie("uname", URLEncoder.encode(Nombre.trim(), "UTF-8"));
				ck.setHttpOnly(true);
				ck.setMaxAge(1800);
				ck.setPath("/");
				response.addCookie(ck);

				Cookie ckEdad = new Cookie("uage", String.valueOf(edad));
				ckEdad.setHttpOnly(true);
				ckEdad.setMaxAge(1800);
				ckEdad.setPath("/");
				response.addCookie(ckEdad);

				Cookie ckEmail = new Cookie("uemail", URLEncoder.encode(email.trim(), "UTF-8"));
				ckEmail.setHttpOnly(true);
				ckEmail.setMaxAge(1800);
				ckEmail.setPath("/");
				response.addCookie(ckEmail);

				Cookie ckGenero = new Cookie("ugender", genero);
				ckGenero.setHttpOnly(true);
				ckGenero.setMaxAge(1800);
				ckGenero.setPath("/");
				response.addCookie(ckGenero);

				Cookie ckEstudiante = new Cookie("ustudent", esEstudiante);
				ckEstudiante.setHttpOnly(true);
				ckEstudiante.setMaxAge(1800);
				ckEstudiante.setPath("/");
				response.addCookie(ckEstudiante);

				
				RequestDispatcher dispatcher = request.getRequestDispatcher("Servlet2");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
