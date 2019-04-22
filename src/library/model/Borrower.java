package library.model;

public class Borrower {
	private int card_id;
	private long ssn;
	private String fname;
	private String lname;
	private String email;
	private String address;
	private String phone;
	public Borrower(int card_id, long ssn, String fname, String lname, String email, String address, String phone) {
		super();
		this.card_id = card_id;
		this.ssn = ssn;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}
	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public long getSsn() {
		return ssn;
	}
	public void setSsn(long ssn) {
		this.ssn = ssn;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
		

}
