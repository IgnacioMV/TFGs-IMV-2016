package ex.upm.dit.isst.tfgs;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.tfgs.dao.EMFService;
import es.upm.dit.isst.tfgs.dao.TFGDAOImpl;
import es.upm.dit.isst.tfgs.model.TFG;

@SuppressWarnings("serial")
public class NewTFG extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain");
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if (req.getUserPrincipal() != null){
			user = req.getUserPrincipal().getName();
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);

		String titulo= req.getParameter("titulo");
		String resumen = req.getParameter("resumen");
		String tutor = req.getParameter("tutor");
		System.out.println("User: "+user);
		System.out.println("Titulo: "+titulo);
		System.out.println("Resumen: "+resumen);
		System.out.println("Tutor: "+tutor);
		if (user != "" && titulo != "" && resumen != "" && tutor != "") {
			EntityManager em = EMFService.get().createEntityManager();
			TFGDAOImpl tfgdao = TFGDAOImpl.getInstance();
			TFG tfg = new TFG(user, titulo, resumen, tutor, "", "", 0);
			tfgdao.createTFG(em, tfg);
			em.close();
			response.sendRedirect("index");
		} else {
			response.sendRedirect("index");
		}
	}
}
