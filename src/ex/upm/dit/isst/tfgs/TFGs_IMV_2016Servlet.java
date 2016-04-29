package ex.upm.dit.isst.tfgs;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.tfgs.dao.EMFService;
import es.upm.dit.isst.tfgs.dao.TFGDAOImpl;
import es.upm.dit.isst.tfgs.model.TFG;


@SuppressWarnings("serial")
public class TFGs_IMV_2016Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/plain");
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
		resp.getWriter().println("funca");
//		EntityManager em = EMFService.get().createEntityManager();
//		TFGDAOImpl tfgdao = TFGDAOImpl.getInstance();
//		TFG tfg = new TFG("Autor1", "Titulo1", "Resumen1", "Tutor1", "Secretario1", "Fichero1", 1);
//		tfgdao.createTFG(em, tfg);
//		em.close();
//		resp.getWriter().println(tfg.getAutor());
//		resp.getWriter().println(tfg.getTitulo());
//		resp.getWriter().println(tfg.getResumen());
//		resp.getWriter().println(tfg.getTutor());
//		resp.getWriter().println(tfg.getSecretario());
//		resp.getWriter().println(tfg.getFichero());
//		resp.getWriter().println(tfg.getEstado());
	}
}
