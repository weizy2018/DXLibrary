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
import bean.library.Borrow;

/**
 * Servlet implementation class ReturnBookServlet
 */
@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int index = Integer.parseInt(request.getParameter("index"));	//所要还的书
		System.out.println("RetuenBookServlet.jsva:" + index);
		
		@SuppressWarnings("unchecked")
		List<Borrow> list = (List<Borrow>)request.getSession().getAttribute("userBorrow");
		String userId = (String)request.getSession().getAttribute("userId");
		double balance = Database.getUserById(userId).getBalance();
		Borrow borrow = list.get(index);
		double money;
		String message = null;
		boolean flag = true;
		if(borrow.getLastTime()-30 > 0){	//逾期了
			money = Math.ceil(borrow.getLastTime()-30)*0.1;
			System.out.println("ReturnBookServlet.java:" + money);
			if(balance<money){	//余额不足，不能还书
				message = "余额不足,还书失败";
				flag = false;
			}
		}else{	//没有逾期
			money = 0;
		}
		if(flag){
			if(Database.returnBook(list.get(index),userId,money)){
				message = "还书成功";
				list = Database.getBorrowInfo(userId);	//从新查找更新借书列表
				request.getSession().setAttribute("userBorrow", list);
			}else{
				message = "还书失败";
			}
		}
		request.setAttribute("message", message);

		RequestDispatcher dis = request.getRequestDispatcher("/mainPage/user/borrowInformation.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
