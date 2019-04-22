package library.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.model.Borrower;
import library.util.DbUtil;

public class BorrowerDAO {
	
	private Connection connection;

    public BorrowerDAO() {
        connection = DbUtil.getConnection();
    }
    int i =0;
    int j =0;

    public int addBorrower(Borrower borrower) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into borrower (ssn,fname,lname,email,address,phone) values (?, ?, ?, ?, ?, ?)");
          
         // Parameters are starting with 1
            preparedStatement.setLong(1, borrower.getSsn());
            preparedStatement.setString(2, borrower.getFname());
            preparedStatement.setString(3, borrower.getLname());
            preparedStatement.setString(4, borrower.getEmail());
            preparedStatement.setString(5, borrower.getAddress());
            preparedStatement.setString(6, borrower.getPhone());
            
            
            i= preparedStatement.executeUpdate();
            
            // to return the generated borrower id
            //PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT LAST_INSERT_ID()");
            //j= preparedStatement.executeUpdate();
            
           System.out.println("addBorrower :"+i);
           

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public int getBorrowerLoanCount(int card_id)
    {
    	int count= 0; 
    	try 
    	{
    		Statement statement = connection.createStatement();
            ResultSet rs = null;
            
            String query = "select * from borrower where card_id ='"+card_id+"'";
            rs = statement.executeQuery(query);
           	if(!rs.next()) 
           	{
           		count = -1;		// no rows returned - card_id does not exist
           	}
           	else 
           	{	
	           	String query1= "select count(*) 'count' from book_loans where card_id ='"+card_id+"' and date_in is null group by card_id"; 
	           	ResultSet rs1 = statement.executeQuery(query1);
	           	while (rs1.next())
	          	{
	       			count = rs1.getInt("count");
	           	}
	       	
           	
           	}  	
           	
        }
    	catch (SQLException e) {
    		count = 4; // assigned 4 to track that exception as occurred and reject the checkout operation in controller
            e.printStackTrace();
        }
    	System.out.println("getBorrowerLoanCount "+count);
    	return count;
    }
    
    public int checkoutBook(int card_id, String isbn)
    {
    	int i=0;
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into book_loans (isbn,card_id,date_out,due_date) SELECT '"+isbn+"','"+card_id+"',curdate(),date_add(curdate(), interval 14 day) WHERE NOT EXISTS (SELECT * FROM book_loans WHERE isbn = '"+isbn+"' AND date_in is null)");
            i= preparedStatement.executeUpdate();
            System.out.println("checkoutBook dbresult:"+i);
       
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	return i;
    }
    

}
