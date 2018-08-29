package bean.library;

import java.sql.Timestamp;

public class OverdueBean {
	private String userId;
	private String userName;
	private String bookId;
	private String bookName;
	private Timestamp borrowDate;
	private int day;
	private double money;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Timestamp getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Timestamp borrowDate) {
		this.borrowDate = borrowDate;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	

}
