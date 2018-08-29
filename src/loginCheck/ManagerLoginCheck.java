package loginCheck;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.library.Manager;
import database.Database;

/**
 * Servlet implementation class ManagerLoginCheck
 */
@WebServlet("/ManagerLoginCheck")
public class ManagerLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("managerLoginCheck:doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("managerLoginCheck:doPost");
		String managerId = request.getParameter("managerId");
		String managerPassword = request.getParameter("managerPassword");
		
		request.setAttribute("managerId", managerId);
		request.setAttribute("managerPassword", managerPassword);
		
		
		String errorMassage = "";
		if(managerId==""){
			errorMassage = "账号不能为空";
			request.setAttribute("error", errorMassage);
			RequestDispatcher dis = request.getRequestDispatcher("login/managerLogin.jsp");
			dis.forward(request, response);
			
		}else if(managerPassword==""){
			errorMassage = "密码不能为空";
			request.setAttribute("error", errorMassage);
			RequestDispatcher dis = request.getRequestDispatcher("login/managerLogin.jsp");
			dis.forward(request, response);
		}else{
			Manager manager = Database.getManagerByManagerId(managerId);
			if(managerId.equals(manager.getManagerId()) && managerPassword.equals(manager.getManagerPassword())){
				HttpSession session = request.getSession();
				session.setAttribute("managerId", managerId);	
				response.sendRedirect("mainPage/managerPage.jsp");
			}else{
				errorMassage = "账号或密码有误";
				request.setAttribute("error", errorMassage);
				RequestDispatcher dis = request.getRequestDispatcher("login/managerLogin.jsp");
				dis.forward(request, response);
			}
		}
	}

}
