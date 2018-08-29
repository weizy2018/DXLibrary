package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import bean.library.OverdueBean;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServlet() {
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
		@SuppressWarnings("unchecked")
		List<OverdueBean> overlist = (List<OverdueBean>) request.getSession().getAttribute("overlist");
		String information = request.getParameter("information");
		System.out.println("SendMessageServlet.java:" + information);
		List<String> usersId = new ArrayList<String>();
		for(int i=0;i<overlist.size();i++){
			String same = overlist.get(i).getUserId();
			usersId.add(same);
			while(i<overlist.size() && overlist.get(i).getUserId().equals(same)){
				i++;
			}
			i--;
		}
		String message = "";
		if(Database.sendMessage(usersId,information)){
			message = "发送成功";
		}else{
			message = "发送失败";
		}
		overlist = Database.overTime();		//再次找找，免得用户有更新
		request.getSession().setAttribute("overlist", overlist);
		
		RequestDispatcher dis = request.getRequestDispatcher("/mainPage/manager/overdue.jsp?message="+message);
		dis.forward(request, response);
	}

}






