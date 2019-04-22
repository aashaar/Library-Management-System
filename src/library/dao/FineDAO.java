package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import library.util.DbUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;


import library.model.Fine;


public class FineDAO {
	private Connection connection;

    public FineDAO() {
        connection = DbUtil.getConnection();
    }
	
    public int payFine(int loan_id)
    {
    	int i=0;
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update fines set paid = 1 where loan_id ='"+loan_id+"'");
            i= preparedStatement.executeUpdate();
            System.out.println("Pay fine dbresult:"+i);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	return i;
    }
    
    
	public List<Fine> getBooksToPayFine() 
	{
//TODO: Display all books which are checked in and give and option to pay fines for them.
//TODO: we need loan id, isbn, borrower fname & lname, checked_in date and fine amount
		 List<Fine> fines = new ArrayList<Fine>();
		try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            String query= "select bl.loan_id,bl.isbn,"
            		+ "bl.card_id, br.fname, br.lname, bl.date_out,bl.due_date,bl.date_in, f.fine_amt, f.paid FROM borrower br, book_loans bl, fines f WHERE (br.card_id = bl.card_id and f.loan_id = bl.loan_id) and paid = false and date_in is not null"; 
        	rs = statement.executeQuery(query);
        	
        	while (rs.next()) {
                Fine fine = new Fine();
                fine.setLoan_id(rs.getInt(1));
                fine.setIsbn(rs.getString(2));
                fine.setCard_id(rs.getInt(3));
                fine.setFname(rs.getString(4));
                fine.setLname(rs.getString(5));
                fine.setDate_out(rs.getDate(6));
                fine.setDue_date(rs.getDate(7));
                fine.setDate_in(rs.getDate(8));
                fine.setFine_amt(rs.getDouble(9));
                fine.setPaid(rs.getBoolean(10));
                fines.add(fine);
                System.out.println(fine.getLoan_id()+" "+fine.getIsbn()+" "+fine.getCard_id()+" "+fine.getFname()+" "+fine.getLname()+" "+fine.getDate_out()+" "+fine.getDue_date()+" "+fine.getDate_in()+" "+fine.getFine_amt()+" "+fine.getPaid());
            }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return fines;
	}
	
	
	public List<Fine> viewBorrowerFines()
	{
		List<Fine> borrowerTotalFines = new ArrayList<Fine>();
		try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            String query= "select br.card_id,br.fname,br.lname,sum(f.fine_amt) from borrower br, fines f, book_loans bl where br.card_id = bl.card_id and bl.loan_id = f.loan_id and paid != 1 group by br.card_id,br.fname,br.lname"; 
        	rs = statement.executeQuery(query);
        	
        	while (rs.next()) {
                Fine fine = new Fine();
                
                fine.setCard_id(rs.getInt(1));
                fine.setFname(rs.getString(2));
                fine.setLname(rs.getString(3));
                fine.setFine_amt(rs.getDouble(4));
                
                borrowerTotalFines.add(fine);
                System.out.println(fine.getCard_id()+" "+fine.getFname()+" "+fine.getLname()+" "+fine.getFine_amt());
            }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return borrowerTotalFines;
		
	}
    
    public void refreshFine()
    {
    	
    	try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            LocalDate localDate = LocalDate.now();
            LocalDate local_today = LocalDate.parse(DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate));
            System.out.println("Local Today: "+local_today);
            java.util.Date today = java.sql.Date.valueOf(local_today);
            System.out.println("Today: " +today);
        
        	//TODO: fetch details from tables in DB
            
        	String query= "select * from book_loans bl left join fines f on bl.loan_id = f.loan_id UNION select * from book_loans bl right join fines f on bl.loan_id = f.loan_id;"; 
        	rs = statement.executeQuery(query);
        	
            while (rs.next()) 
            {
                
                int bl_loan_id = rs.getInt(1);
                String isbn = rs.getString(2);
                int card_id = rs.getInt(3);
                Date date_out = rs.getDate(4);
                Date due_date = rs.getDate(5);
                Date date_in = rs.getDate(6);
                int f_loan_id = rs.getInt(7);
                Double fine_amt = rs.getDouble(8);
                boolean paid = rs.getBoolean(9);
             
                System.out.println(bl_loan_id);
                System.out.println(isbn);
                System.out.println(card_id);
                System.out.println(date_out);
                System.out.println(due_date);
                System.out.println(date_in);
                System.out.println(f_loan_id);
                System.out.println(fine_amt);
                System.out.println(paid);
               
                
                //TODO:calculating fines 
                
                //check if book is checked in or not
                long diff;
                long diffDays = 0;
                boolean flag = false;
                if(date_in == null) // book is not checked in
                {
                	if(today.after(due_date)) // checked out and overdue
                	{
                		diff = today.getTime() - due_date.getTime();
                		diffDays = diff / (24 * 60 * 60 * 1000);
                		fine_amt = diffDays * 0.25;
                		System.out.println("Book is overdue "+diffDays+" days");
                		flag = true;
                		// write fine into database
                		/*int i=0;
                    	PreparedStatement preparedStatement = connection
                                    .prepareStatement("insert into fines values ('"+bl_loan_id+"','"+fine_amt+"',0) on duplicate key update fine_amt = '"+fine_amt+"'");
                    	i= preparedStatement.executeUpdate();
                    	System.out.println("Fine Insert/Update 01 dbresult:"+i);*/
                		
                	}
                	else // checked out but not yet overdue
                	{
                		System.out.println("Book is checked out but not overdue yet");
                		System.out.println("Fine is $"+fine_amt);
                	}
                }
            	
                else // book is  checked in
                {
                	if(date_in.after(due_date)) // checked in but overdue
                	{
	                	if(paid == false) // fine not paid yet
	                	{
	                		flag = true;
	                		diff = date_in.getTime() - due_date.getTime();
	                		diffDays = diff / (24 * 60 * 60 * 1000);
	                	//	fine_amt = diffDays * 0.25;
	                		System.out.println("Book is checked in but was overdue for "+diffDays+" days");
	                	//	System.out.println("Fine is $"+fine_amt);
	                		
	                		/*int i=0;
	                    	PreparedStatement preparedStatement = connection
	                                    .prepareStatement("insert into fines values ('"+bl_loan_id+"','"+fine_amt+"',0) on duplicate key update fine_amt = '"+fine_amt+"'");
	                    	i= preparedStatement.executeUpdate();
	                    	System.out.println("Fine Insert/Update 02 dbresult:"+i);*/
	                	}
	                	else // fine already paid
	                	{
	                		// book was checked in and fine was paid - Do nothing
	                		System.out.println("book was checked in and fine was paid - Do nothing");
	                	}
                	}
                	else // checked in and not overdue
                	{
                		// book was checked in before due date and there is no fine- Do nothing
                		System.out.println("book was checked in before due date and there is no fine - Do nothing");
                	
                	}
                	
                }
                
                if(flag)
                {
	                fine_amt = diffDays *0.25;
	                System.out.println("Fine is $"+fine_amt);
	                int i=0;
	            	PreparedStatement preparedStatement = connection
	                            .prepareStatement("insert into fines values ('"+bl_loan_id+"','"+fine_amt+"',0) on duplicate key update fine_amt = '"+fine_amt+"'");
	            	i= preparedStatement.executeUpdate();
	            	System.out.println("Fine Insert/Update dbresult:"+i);
	            }
                
                System.out.println("------------");
            }
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    
    }
}
