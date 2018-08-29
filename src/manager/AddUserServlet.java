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
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
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
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String address = request.getParameter("address");
		User user = new User();
		user.setId(userId);
		user.setName(userName);
		user.setPassword(userPassword);
		user.setPhone(phone);
		user.setMail(mail);
		user.setAddress(address);
		boolean success = Database.addUser(user);
		String message;
		if(success){
			message = "添加成功";
		}else{
			message = "添加失败";
		}
		request.setAttribute("message", message);
		request.setAttribute("user",user);
		RequestDispatcher  dis = request.getRequestDispatcher("/mainPage/manager/addUser.jsp?");
		dis.forward(request, response);
		
		
	}

}
