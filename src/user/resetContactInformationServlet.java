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

/**
 * Servlet implementation class resetContactInformationServlet
 */
@WebServlet("/resetContactInformationServlet")
public class resetContactInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetContactInformationServlet() {
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
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String address = request.getParameter("address");
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		RequestDispatcher dis = null;
		
		boolean success = Database.updataCatact(userId, phone, mail, address);
		if(!success){
			request.setAttribute("message", "修改失败");
			dis = request.getRequestDispatcher("/mainPage/user/resetContactInformation.jsp");
		}else{
			dis = request.getRequestDispatcher("/mainPage/user/resetSuccess.jsp");
		}
		dis.forward(request, response);
		
	}

}
