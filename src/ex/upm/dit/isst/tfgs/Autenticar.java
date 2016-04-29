package ex.upm.dit.isst.tfgs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.*;

import es.upm.dit.isst.tfgs.dao.EMFService;
import es.upm.dit.isst.tfgs.dao.TFGDAOImpl;
import es.upm.dit.isst.tfgs.model.TFG;

@SuppressWarnings("serial")
public class Autenticar extends HttpServlet {
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
		
		EntityManager em = EMFService.get().createEntityManager();
		TFGDAOImpl tfgdao = TFGDAOImpl.getInstance();
		ArrayList<TFG> tfgs = new ArrayList<TFG>();
		List<TFG> savedTFGs = tfgdao.readTFGs(em);
		if (savedTFGs != null) {
			for(TFG tfg : tfgdao.readTFGs(em)) {
				//tfgdao.deleteTFG(em, tfg);
				//System.out.println(tfg.getAutor());
				tfgs.add(tfg);
			}
		}
		req.getSession().setAttribute("tfgs", tfgs);
		em.close();
		
		System.out.println("AutenticarServlet");
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);
	}
}
