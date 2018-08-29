package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import bean.library.Book;
import bean.library.Borrow;

/**
 * Servlet implementation class RenewSerlvlet
 */
@WebServlet("/RenewServlet")
public class RenewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Borrow borrow = (Borrow)request.getSession().getAttribute("borrowInfo");
		@SuppressWarnings("unchecked")
		List<Borrow> list = (List<Borrow>) request.getSession().getAttribute("userBorrow");
		
		int i = Integer.parseInt((String)request.getParameter("borrowInfo"));
		System.out.println("RenewServlet.java:" + list.get(i));
		
		String userId = (String)request.getSession().getAttribute("userId");
		
		String message = "";
		if(Database.RenewBook(userId, list.get(i))){
			message = "续借成功";
		}else{
			message = "续借失败";
		}
		
		List<Borrow> borrowList = Database.getBorrowInfo(userId);
		request.getSession().setAttribute("userBorrow", borrowList);
		
		request.setAttribute("userBorrow", borrowList);
		request.setAttribute("message", message);
		RequestDispatcher dis = request.getRequestDispatcher("mainPage/user/borrowInformation.jsp");
		dis.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
