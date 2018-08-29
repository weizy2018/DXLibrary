package bean.library;

import java.sql.Timestamp;

public class Borrow {
	private String bookName;
	private String bookId;
	private String publisher;
	private String publishTime;
	private String author;
	private Timestamp borrowDate;
	private Timestamp deadline;
	private double lastTime;
	public Borrow(){
		
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Timestamp getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Timestamp borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public double getLastTime() {
		return lastTime;
	}

	public void setLastTime(double lastTime) {
		this.lastTime = lastTime;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	

}
