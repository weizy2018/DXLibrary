package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import bean.library.User;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		String userId = request.getParameter("userid");
		user.setId(userId);
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
		user.setMail(request.getParameter("mail"));
		user.setAddress(request.getParameter("addr"));
		String oldID = request.getParameter("oldID");
		String message = "";
		if(Database.updateUserInfo(user, oldID)){
			message = "修改成功";
		}else{
			message = "修改失败";
		}
		RequestDispatcher dis = request.getRequestDispatcher("/mainPage/manager/editUserInfo.jsp?message=" + message + "&userId=" + userId);
		dis.forward(request, response);
		
	}

}
