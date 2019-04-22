package library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.model.BookLoans;
import library.util.DbUtil;

public class BookLoansDAO {
	
	private Connection connection;

    public BookLoansDAO() {
        connection = DbUtil.getConnection();
    }
	
    public List<BookLoans> getBooksforCheckIn(String checkinSearch) {
        List<BookLoans> bookloans = new ArrayList<BookLoans>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            
            	String query= "Select distinct bl.loan_id, b.isbn, b.title, br.fname, br.lname, bl.date_out,bl.due_date from books b, borrower br,book_loans bl where  (b.isbn = bl.isbn and br.card_id = bl.card_id) and (bl.date_in is null) and ((b.isbn like '%"+checkinSearch+"%') or (b.title like '%"+checkinSearch+"%') or (br.card_id like '%"+checkinSearch+"%') or (br.fname like '%"+checkinSearch+"%') or (br.lname like '%"+checkinSearch+"%'));"; 
            	rs = statement.executeQuery(query);            
            
            
            while (rs.next()) {
                BookLoans bookloan = new BookLoans();
                bookloan.setLoan_id(rs.getInt("loan_id"));
                bookloan.setIsbn(rs.getString("isbn"));
                bookloan.setTitle(rs.getString("title"));
                bookloan.setFname(rs.getString("fname"));
                bookloan.setLname(rs.getString("lname"));
                bookloan.setDate_out(rs.getDate("date_out"));
                bookloan.setDue_date(rs.getDate("due_date"));
                
                bookloans.add(bookloan);
                System.out.println(rs.getInt("loan_id"));
                System.out.println(rs.getString("isbn"));
                System.out.println(rs.getString("title"));
                System.out.println(rs.getString("fname"));
                System.out.println(rs.getString("lname"));
                System.out.println(rs.getDate("date_out"));
                System.out.println(rs.getDate("due_date"));
                
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookloans;
    }
    
    public int checkinBook(int loan_id)
    {
    	int i=0;
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update book_loans set date_in = curdate() where loan_id  = '"+loan_id+"';");
             i= preparedStatement.executeUpdate();
           System.out.println("checkinBook dbresult:"+i);
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	return i;
    }

}
