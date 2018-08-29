package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.library.*;

public class Database {
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dx_library";
			Connection conn = DriverManager.getConnection(url,"root","123456");
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void release(Connection conn, PreparedStatement stm, ResultSet rs){
		try {
			if(rs!=null)rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(stm!=null)stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//查找指定id的用户信息
	public static User getUserById(String userId){
		User user = new User();
		
		Connection conn=null ;
		PreparedStatement stm=null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			String sql="select *  from `user` where id=?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userId);
			rs = stm.executeQuery();
			if(rs.next()){
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setAddress(rs.getString(5));
				user.setMail(rs.getString(6));
				user.setBalance(rs.getDouble(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		return user;
	}
	//修改用户密码
	public static boolean updatePassword(String id,String newPassword){
		boolean success = true;
		Connection conn=null ;
		PreparedStatement stm=null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "update user set password=? where id = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, newPassword);
			stm.setString(2, id);
			stm.executeUpdate();
			
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		return success;
				
	}
	//修改用户联系方式
	public static boolean updataCatact(String userId,String phone,String mail,String address){
		boolean success = true;		
		Connection conn=null ;
		PreparedStatement stm=null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "update user set phone = ?,address = ?,mail = ? where id = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, phone);
			stm.setString(2, address);
			stm.setString(3, mail);
			stm.setString(4, userId);
			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			success = false;
		}finally{
			Database.release(conn, stm, rs);
		}
		
		return success;
	}
	//获取指定id的用户的借阅信息
	public static List<Borrow> getBorrowInfo(String userId){
		List<Borrow> list = new ArrayList<Borrow>();
		Connection conn=null ;
		PreparedStatement stm=null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "select book_name,book.book_id,publisher,publish_time,author,borrow_date " 
						+ "from book,borrow,user " 
						+ "where user.id = ? and book.book_id = borrow.book_id and user.id = borrow.id";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userId);
			rs = stm.executeQuery();
			while(rs.next()){
				Borrow borrow = new Borrow();
				borrow.setBookName(rs.getString(1));
				borrow.setBookId(rs.getString(2));
				borrow.setPublisher(rs.getString(3));
				borrow.setPublishTime(rs.getString(4));
				borrow.setAuthor(rs.getString(5));
				borrow.setBorrowDate(rs.getTimestamp(6));
				
				Timestamp t = borrow.getBorrowDate();
				Date d = new Date(t.getTime());
				Time tt = new Time(t.getTime());
				System.out.println("Database::timestamp:" + t + "  Date:" + d + "  Time:" + tt);
				
				
				long time = 30*24*60*60*1000L;	//三十天的秒数	
				Timestamp deadline = new Timestamp(borrow.getBorrowDate().getTime() + time);
				borrow.setDeadline(deadline); 
				
//				System.out.println("long time = 30*24*60*60*1000L;" + time);
//				System.out.println("borrow.getBorrowDate().getTime():" + borrow.getBorrowDate().getTime());
//				System.out.println("borrow.getBorrowDate().getTime() + time" + deadline.getTime());
				
				Timestamp nowTime = new Timestamp(System.currentTimeMillis());
				
				double last = (float)(nowTime.getTime()-borrow.getBorrowDate().getTime())/(1000*60*60*24);	//已经借出的天数
				//System.out.println("last:" + last);
				borrow.setLastTime(last);
				list.add(borrow);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		return list;
		
	}
	
	//通过指定管理员账号查找管理员信息
	public static Manager getManagerByManagerId(String managerId){
		Manager manager = new Manager();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "select * from manager where account = ?";
		try {
			conn = Database.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, managerId);
			rs = stm.executeQuery();
			while(rs.next()){
				manager.setManagerId(rs.getString(1));
				manager.setManagerPassword(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		
		return manager;
		
	}
	public static List<User> getAllUser(){
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "select * from user";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setAddress(rs.getString(5));
				user.setMail(rs.getString(6));
				userList.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		return userList;
	}
	public static boolean addUser(User user){
		//INSERT INTO `dx_library`.`user` (`id`, `name`, `password`, `phone`, `address`, `mail`) VALUES ('789', 'wangwu', '123456', '6666666', 'dongbei', '123456@qq.com');
		boolean success = true;
		Connection conn = null;
		PreparedStatement stm = null;
		try{
			conn = Database.getConnection();
			String sql = "insert into user(id,name,password,phone,address,mail) values (?,?,?,?,?,?)";
			stm = conn.prepareStatement(sql);
			stm.setString(1, user.getId());
			stm.setString(2, user.getName());
			stm.setString(3, user.getPassword());
			stm.setString(4, user.getPhone());
			stm.setString(5, user.getAddress());
			stm.setString(6, user.getMail());
			stm.executeUpdate();
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, null);
			
		}
		return success;

	}
	//查找用户借书的数量
	public static int countBooks(String userId){
		int n = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "select count(*) from borrow where id = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userId);
			rs = stm.executeQuery();
			while(rs.next()){
				n = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		return n;
	}
	//借书 	//此处可用事物处理机制
	@SuppressWarnings("resource")
	public static boolean borrowBook(String userId,String bookId){
		boolean success = true;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			//被借书的数量减少
			String updataSql = "update book set amount = amount-1 where book_id = ?";
			stm = conn.prepareStatement(updataSql);
			stm.setString(1, bookId);
			stm.executeUpdate();
			//用户借书的记录加1
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String insertSql = "insert into borrow values(?,?,?)";
			stm = conn.prepareStatement(insertSql);
			stm.setString(1, userId);
			stm.setString(2, bookId);
			stm.setTimestamp(3, time);
			stm.execute();

		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		return success;
	}
	//获取全部的图书
	public static List<Book> getAllBooks(){
		List<Book> bookList = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "select * from book";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setId(rs.getString(1));
				book.setName(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setPublishTime(rs.getString(4));
				book.setPublisher(rs.getString(5));
				book.setAuthor(rs.getString(6));
				book.setAmount(rs.getInt(7));
				bookList.add(book);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		
		return bookList;
	}
	//按关键词获取图书
	public static List<Book> getBooksByKeyWord(String keyword){
		List<Book> bookList = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		keyword = "%" + keyword + "%";
		try {
			conn = Database.getConnection();
			String sql = "select * from book where book_name like ? or category like ? or author like ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, keyword);
			stm.setString(2, keyword);
			stm.setString(3, keyword);
			rs = stm.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setId(rs.getString(1));
				book.setName(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setPublishTime(rs.getString(4));
				book.setPublisher(rs.getString(5));
				book.setAuthor(rs.getString(6));
				book.setAmount(rs.getInt(7));
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		return bookList;
	}
	//续借图书
	public static boolean RenewBook(String userId,Borrow b){
		boolean success = true;
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			//update borrow set borrow_date = '2018-04-28 09:00:32' where id = '123' and book_id = '004' and borrow_date = '2018-04-30 09:00:32'
			String sql = "update borrow set borrow_date = ? where id = ? and book_id = ? and borrow_date = ?";
			stm = conn.prepareStatement(sql);
			stm.setTimestamp(1, now);
			stm.setString(2, userId);
			stm.setString(3, b.getBookId() );
			stm.setTimestamp(4, b.getBorrowDate());
			stm.executeUpdate();

		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		
		return success;
	}
	//修改管理员密码
	public static boolean updateManagerPassword(String managerId,String newPassword){
		boolean success = true;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "update manager set password= ? where account = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, newPassword);
			stm.setString(2, managerId);
			stm.executeUpdate();
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		return success;
	}
	//通过编号或者姓名查找读者
	public static List<User> getUserByIdOrName(String name){
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "select * from user where name=? or id = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, name);
			rs = stm.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setAddress(rs.getString(5));
				user.setMail(rs.getString(6));
				userList.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
		}
		
		return userList;
	}
	//select * from borrow where (borrow_date+30)>'2018-04-30 10:00:00'
	//"select user.id, user.name, book.book_id, book.book_name, borrow_date" + "from user, book,borrow" + "where user.id = borrow.id and book.book_id = borrow.book_id" + "and borrow_date < '2018-03-31 16:07:13.855'";
	public static List<OverdueBean> overTime(){
		
		List<OverdueBean> overlist = new ArrayList<OverdueBean>();
		long mis = 30*24*60*60*1000L;

		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		Timestamp len = new Timestamp(now.getTime()-mis);	//减去30天后的日期
		
		//System.out.println("AAAAAAAAAAAAAAAA  Database.java:" + len);
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		String sql = "select user.id, user.name, book.book_id, book.book_name, borrow_date " 
					+ "from user, book,borrow " 
					+ "where user.id = borrow.id and book.book_id = borrow.book_id " 
					+ "and borrow_date < ? "
					+ "order by user.id";
		try{
			conn = Database.getConnection();
			stm = conn.prepareStatement(sql);

			stm.setTimestamp(1, len);
			
			rs = stm.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getTimestamp(5));
				OverdueBean over = new OverdueBean();
				over.setUserId(rs.getString(1));
				over.setUserName(rs.getString(2));
				over.setBookId(rs.getString(3));
				over.setBookName(rs.getString(4));
				
				Timestamp borrowTime = rs.getTimestamp(5);
				
				over.setBorrowDate(borrowTime);
				
				double day = (double)(now.getTime()-borrowTime.getTime()-mis)/(24*60*60*1000*1.0);
				System.out.println("Database.java:" + day);
				over.setDay((int)Math.ceil(day));	//设置逾期天数
				System.out.println("Database.java:AAAAAAAAAAAAAAAAA:" + (double) (over.getDay()*0.1));
				over.setMoney((double) (over.getDay()*0.1));	//设置罚款金额
				overlist.add(over);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		return overlist;
	}
	public static boolean sendMessage(List<String> usersId, String information) {
		boolean success = true;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Timestamp time = new Timestamp(System.currentTimeMillis());
		//insert into message(user_id,msg,time) values('006','还书','2018-04-30 23:14:00')
		try{
			conn = Database.getConnection();
			String sql = "insert into message(user_id,msg,time) values(?,?,?)";
			int i=0;
			while(i<usersId.size()){
				System.out.println("Database.java:" + usersId.get(i));
				stm = conn.prepareStatement(sql);
				stm.setString(1, usersId.get(i));
				stm.setString(2, information);
				stm.setTimestamp(3, time);
				stm.execute();
				i++;
			}
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		
		return success;
		
	}
	public static List<Message> getSystemMessage(String userId) {
		// TODO Auto-generated method stub
		List<Message> msglist = new ArrayList<Message>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "select msg, time from message where user_id = ? order by time desc";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userId);
			rs = stm.executeQuery();
			while(rs.next()){
				Message m = new Message();
				m.setMessage(rs.getString(1));
				m.setTime(rs.getTimestamp(2));
				msglist.add(m);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		
		return msglist;
	}
	public static boolean updateUserInfo(User user,String oldID){
		boolean success = true;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		//UPDATE `dx_library`.`user` SET `id`='45626', `name`='abc', `password`='fjsd', `phone`='5651', `address`='sdjnn', `mail`='sdjfod46564' WHERE `id`='56526';
		try{
			conn = Database.getConnection();
			String sql = "update user set id = ?,name = ?,password = ?,phone = ?,address = ?,mail = ? where id = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, user.getId());
			stm.setString(2, user.getName());
			stm.setString(3, user.getPassword());
			stm.setString(4, user.getPhone());
			stm.setString(5, user.getAddress());
			stm.setString(6, user.getMail());
			stm.setString(7, oldID);
			stm.executeUpdate();
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			Database.release(conn, stm, rs);
			
		}
		return success;
	}
	public static boolean returnBook(Borrow borrow,String userId,double money) {
		//delete from borrow where id = '123' and book_id = '001' and borrow_date = '2018-03-29 23:36:45'
		//update book set amount = amount+1 where book_id = '001'
		boolean success = true;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			conn.setAutoCommit(false);
			//删除一条借书记录
			String sql = "delete from borrow where id = ? and book_id = ? and borrow_date = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userId);
			stm.setString(2, borrow.getBookId());
			stm.setTimestamp(3, borrow.getBorrowDate());
			stm.execute();
			
			//修改读者的余额
			if(money>0){
				sql = "update user set balance = balance-? where id = ?";
				stm = conn.prepareStatement(sql);
				stm.setDouble(1, money);
				stm.setString(2, userId);
				stm.executeUpdate();
			}
			
			//相应的图书数量+1
			sql = "update book set amount = amount+1 where book_id = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, borrow.getBookId());
			stm.executeUpdate();
			conn.commit();
		}catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			success = false;
		}
		
		return success;
		
	}
	public static boolean deleteUser(String userId){
		boolean success = true;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			conn = Database.getConnection();
			String sql = "delete from user where id = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userId);
			
			stm.execute();
		}catch(SQLException e){
			e.printStackTrace();
			success = false;
		}finally{
			Database.release(conn, stm, rs);
			
		}
		return success;
	}

}






