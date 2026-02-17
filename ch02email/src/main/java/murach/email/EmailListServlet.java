package murach.email;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import murach.business.User;
// import murach.data.UserDB; // Comentamos esto para que no pida la clase DB

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String url = "/index.html";
        
        // Obtener la acción actual
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // acción por defecto
        }
        
        // Realizar acción y setear la URL a la página apropiada
        if (action.equals("join")) {
            url = "/index.html";    // la página de registro
        }
        else if (action.equals("add")) {
            // Obtener parámetros de la petición
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            
            // Guardar datos en objeto User
            User user = new User(firstName, lastName, email);
            
            // UserDB.insert(user); // ESTO ESTÁ COMENTADO: No usamos DB por ahora
            
            // Poner el objeto User en el objeto request para que lo use el JSP
            request.setAttribute("user", user);
            url = "/thanks.jsp";    // la página de agradecimiento
        }
        
        // Redirigir (forward) la petición y respuesta a la URL especificada
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
}