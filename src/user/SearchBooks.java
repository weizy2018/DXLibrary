package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import bean.library.Book;

/**
 * Servlet implementation class SearchBooks
 */
@WebServlet("/SearchBooks")
public class SearchBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBooks() {
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
		String keywords = request.getParameter("keywords");
		List<Book> bookList = Database.getBooksByKeyWord(keywords);
		System.out.println("SearchBook.java:" + bookList.size());
		request.setAttribute("searchBook", bookList);
		RequestDispatcher dis = request.getRequestDispatcher("mainPage/user/libraryBooks.jsp?first=1");
		dis.forward(request, response);
	}

}
