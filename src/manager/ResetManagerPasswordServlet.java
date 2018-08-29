package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.library.Manager;
import bean.library.User;
import database.Database;

/**
 * Servlet implementation class ResetManagerPasswordServlet
 */
@WebServlet("/ResetManagerPasswordServlet")
public class ResetManagerPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetManagerPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String surePassword = request.getParameter("surePassword");
		
		RequestDispatcher dis = null;
		String message = "";
		
		System.out.println("old:" + oldPassword + "  " + "new:" + newPassword + "  " + "surePasssword:" + surePassword);
		if(!newPassword.equals(surePassword)){
			message = "两次新密码不一致";
			dis = request.getRequestDispatcher("/mainPage/manager/resetManagerPassword.jsp?message=" + message);
		}else{
			HttpSession session = request.getSession();
			String managerId = (String)session.getAttribute("managerId");
			System.out.println("ResetManagerPassword.java:" + managerId);
			
			//查找原来的密码
			Manager manager = Database.getManagerByManagerId(managerId);
			
			if(manager.getManagerPassword().equals(oldPassword)){
				//修改密码
				boolean success = Database.updateManagerPassword(managerId, newPassword);
				if(!success){
					message = "密码修改失败";
					dis = request.getRequestDispatcher("/mainPage/manager/resetManagerPassword.jsp?message=" + message);
				}else{
					//密码修改成功
					dis = request.getRequestDispatcher("/mainPage/user/resetSuccess.jsp");
				}
			}else{
				message = "原密码不正确";
				dis = request.getRequestDispatcher("/mainPage/manager/resetManagerPassword.jsp?message=" + message);
			}
		}
		
		dis.forward(request, response);
	}

}
