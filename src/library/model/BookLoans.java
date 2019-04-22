package library.model;

import java.util.Date;



public class BookLoans {
	private int loan_id;
	private String isbn;
	private String title;
	private String fname;
	private String lname;
	private Date date_out;
	private Date due_date;
	public BookLoans(int loan_id, String isbn, String title, String fname, String lname, Date date_out, Date due_date) {
		super();
		this.loan_id = loan_id;
		this.isbn = isbn;
		this.title = title;
		this.fname = fname;
		this.lname = lname;
		this.date_out = date_out;
		this.due_date = due_date;
	}
	public BookLoans() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Date getDate_out() {
		return date_out;
	}
	public void setDate_out(Date date_out) {
		this.date_out = date_out;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	
	
}
