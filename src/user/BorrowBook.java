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
 * Servlet implementation class BorrowBook
 */
@WebServlet("/BorrowBook")
public class BorrowBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowBook() {
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
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		String bookId = request.getParameter("bookId");
		System.out.println("BorrowBook.java:" + bookId);
		System.out.println("bookId:" +bookId);
		String message = "";
		if(Database.countBooks(userId)>=3){
			System.out.println("BorrowBook:" + "借书达到三本");
			message = "你的借书量以达到3本,请归还后再借";
		}else{
			//可以借书
			if(Database.borrowBook(userId, bookId)){
				System.out.println("BorrowBook:" + "借书成功");
				message = "借书成功";
			}else{
				System.out.println("BorrowBook:" + "借书失败");
				message = "借书失败";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher dis = request.getRequestDispatcher("/mainPage/user/libraryBooks.jsp?first=1");
		dis.forward(request, response);
	}

}





