package library.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.model.Book;
import library.util.DbUtil;
public class BookDAO {
    
	
	private Connection connection;

    public BookDAO() {
        connection = DbUtil.getConnection();
    }


    public List<Book> getBooksBySearchString(String searchString) {
        List<Book> books = new ArrayList<Book>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            if (searchString == null) {
            	 rs = statement.executeQuery("select * from books");
            }
            else {
            	int limit =1;
            	int offset =0;
            	// rs = statement.executeQuery("select * from books where title like '%"+searchString+"%' limit 100");
            	String query= "Select distinct b.isbn, b.title,group_concat(a.name) 'authors',(select case when bl.date_in IS NULL then 'false' else 'true' end from book_loans bl where bl.isbn = b.isbn order by bl.loan_id desc limit "+offset+","+limit+") 'available' from books b,authors a,book_authors ba where (b.isbn = ba.isbn and a.author_id = ba.author_id) and ((b.isbn like '%"+searchString+"%') or (b.title like '%"+searchString+"%') or (a.name like '%"+searchString+"%')) group by b.isbn,b.title"; 
            	rs = statement.executeQuery(query);
            	
            }
            
            
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("authors"));
                book.setAvailable(rs.getString("available"));
                books.add(book);
                System.out.println(book.getIsbn()+" "+book.getTitle()+" "+book.getAuthor());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book getBookByIsbn(String isbn) {
        Book book = new Book();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            
            	// rs = statement.executeQuery("select * from books where title like '%"+searchString+"%' limit 100");
            	int limit = 1;
            	int offset = 0;
            	String query= "Select distinct b.isbn, b.title,group_concat(a.name) 'authors',(select case when bl.date_in IS NULL then 'false' else 'true' end from book_loans bl where bl.isbn = b.isbn order by bl.loan_id desc limit "+offset+","+limit+") 'available' from books b,authors a,book_authors ba where (b.isbn = ba.isbn and a.author_id = ba.author_id) and (b.isbn ='"+isbn+"') group by b.isbn,b.title"; 
            	rs = statement.executeQuery(query);
            
            
            
            while (rs.next()) {
                
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("authors"));
                book.setAvailable(rs.getString("available"));
                
                System.out.println(book.getIsbn()+" "+book.getTitle()+" "+book.getAuthor());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }
    


}
