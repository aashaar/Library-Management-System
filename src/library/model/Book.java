package library.model;

import java.util.List;
import library.model.Borrower;
public class Book {
	private String isbn;
	private String title;
	private String author;
	private String available;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String isbn, String title, String author, String available) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.available = available;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
			
			
}