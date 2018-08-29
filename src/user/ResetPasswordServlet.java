package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import bean.library.User;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ResetPasswordSevelet.java:doGeet");
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
		
		if(!newPassword.equals(surePassword)){
			request.setAttribute("message", "两次新密码不一致");
			dis = request.getRequestDispatcher("/mainPage/user/resetPassword.jsp");
		}else{
			HttpSession session = request.getSession();
			String userId = (String)session.getAttribute("userId");
			User user = Database.getUserById(userId);
			if(user.getPassword().equals(oldPassword)){
				//修改密码
				boolean success = Database.updatePassword(userId, newPassword);
				if(!success){
					request.setAttribute("message", "密码修改失败");
					dis = request.getRequestDispatcher("/mainPage/user/resetPassword.jsp");
				}else{
					//request.setAttribute("message", "密码修改成功");
					dis = request.getRequestDispatcher("/mainPage/user/resetSuccess.jsp");
				}
			}else{
				request.setAttribute("message", "原密码不正确");
				dis = request.getRequestDispatcher("/mainPage/user/resetPassword.jsp");
			}
		}
		//response.sendRedirect("mainPage/user/resetPassword.jsp");
		
		dis.forward(request, response);
	}

}











