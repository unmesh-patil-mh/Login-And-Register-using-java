package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean status = UserDao.validate(username, password);
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		if(status) {
			HttpSession session = req.getSession();
			session.setAttribute("usernsme", username);
			out.print("Login Succesfull");
		}else {
			out.print("Invalid Username or Password");
		}
	}
}
