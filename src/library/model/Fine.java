package library.model;

import java.math.BigDecimal;
import java.util.Date;

public class Fine {
	
	private int loan_id;
	private String isbn;
	private int card_id;
	private Date date_out;
	private Date due_date;
	private Date date_in;
	private String fname;
	private String lname;
	private Double fine_amt;
	private boolean paid;
	
	public Fine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fine(int loan_id, String isbn, int card_id, Date date_out, Date due_date, Date date_in, String fname,
			String lname, Double fine_amt, boolean paid) {
		super();
		this.loan_id = loan_id;
		this.isbn = isbn;
		this.card_id = card_id;
		this.date_out = date_out;
		this.due_date = due_date;
		this.date_in = date_in;
		this.fname = fname;
		this.lname = lname;
		this.fine_amt = fine_amt;
		this.paid = paid;
	}

	public Fine(int card_id, String fname, String lname, Double fine_amt, boolean paid) {
		super();
		this.card_id = card_id;
		this.fname = fname;
		this.lname = lname;
		this.fine_amt = fine_amt;
		this.paid = paid;
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

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
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

	public Date getDate_in() {
		return date_in;
	}

	public void setDate_in(Date date_in) {
		this.date_in = date_in;
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

	public Double getFine_amt() {
		return fine_amt;
	}

	public void setFine_amt(Double fine_amt) {
		this.fine_amt = fine_amt;
	}

	public boolean getPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	
	
			
}

