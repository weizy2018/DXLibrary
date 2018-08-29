package loginCheck;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.library.User;
import database.Database;

/**
 * Servlet implementation class UserLoginCheck
 */
@WebServlet("/UserLoginCheck")
public class UserLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UserLoginCheck:doGet");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		request.setAttribute("id", id);
		request.setAttribute("password", password);
		RequestDispatcher dis = request
				.getRequestDispatcher("login/userLogin.jsp");

		User user = Database.getUserById(id);
		String error = "";
		if (id == "") {
			error = "账号不能为空";
			request.setAttribute("error", error);
			dis.forward(request, response);

		} else if (password == "") {
			error = "密码不能为空";
			request.setAttribute("error", error);
			dis.forward(request, response);
		} else if (user.getId() != null) {
			HttpSession session = request.getSession();

			if (user.getId().equals(id) && user.getPassword().equals(password)) {
				session.setAttribute("userId", id);
				response.sendRedirect("mainPage/userPage.jsp");

			} else {
				error = "账号或者密码不正确";
				request.setAttribute("error", error);
				dis.forward(request, response);

			}
		} else {

			error = "账号或者密码不正确";
			request.setAttribute("error", error);
			dis.forward(request, response);
		}

	}

}
