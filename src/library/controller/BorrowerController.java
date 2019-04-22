package library.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.model.Book;
import library.model.Borrower;
import library.dao.BorrowerDAO;


public class BorrowerController extends  HttpServlet
{

	private static String ADD = "/addBorrower.jsp";
	private static String RESULT = "/resultCheckout.jsp";
	
	
	private BorrowerDAO dao;
 
    public BorrowerController() {
        super();
        dao = new BorrowerDAO();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    String forward="";
	    String action = request.getParameter("action");
	    System.out.println(action);
	    if (action.equalsIgnoreCase("add")){
	    	forward = ADD;
	    	System.out.println(forward);
	    }
	    else if (action.equalsIgnoreCase("checkout"))
        {
	    	forward = RESULT;
            int card_id = Integer.parseInt(request.getParameter("card_id"));
            String isbn = request.getParameter("isbn");
            /////////////////////
            int count_loans = dao.getBorrowerLoanCount(card_id);
            if (count_loans >= 0 && count_loans < 3)
            {
            	int return_i = 0;
            	return_i = dao.checkoutBook(card_id,isbn);
            	System.out.println(return_i);
            	if(return_i ==1) 
            	{
            		request.setAttribute("checkedout", true);
                	System.out.println("checkout successful");
            	}
            	else
            	{
            		request.setAttribute("checkedout", false);
                	System.out.println("checkout failed");
            	}
            	
            	
            }
           
            else if(count_loans == -1)
            {
            	request.setAttribute("borrowerNotExist", true);
            	System.out.println("checkout failed - borrowed does not exist");
            	
            }
            else 
            {
            	request.setAttribute("loanQuotaExceeded", true);
            	System.out.println("checkout failed - loan quota exceeded by borrower");
            	
            }
            
        }
	    
	    System.out.println(forward);
	    RequestDispatcher view = request.getRequestDispatcher(forward);
	    view.forward(request, response);
		    
	}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
	       
			String forward = "";
			Borrower borrower = new Borrower();
			int card_id = 0;
			borrower.setCard_id(card_id);
			borrower.setFname(request.getParameter("fname"));
			borrower.setLname(request.getParameter("lname"));
			
			String ssn = request.getParameter("ssn"); //since ssn is of type Long
			borrower.setSsn(Long.parseLong(ssn));
			
			borrower.setAddress(request.getParameter("address"));
			borrower.setEmail(request.getParameter("email"));
			
			borrower.setPhone(request.getParameter("phone"));
			
			System.out.println("Done !");
			int return_i=0;
			return_i= dao.addBorrower(borrower);
			System.out.println("add borrower step DB return value: "+return_i);
			request.setAttribute("ssn_duplicate", false);
			request.setAttribute("success", false);
			
			if(return_i !=1)
			{
				request.setAttribute("ssn_duplicate", true);
				RequestDispatcher view = request.getRequestDispatcher(ADD);
				view.forward(request, response);
			}
			else
			{
				request.setAttribute("success", true);
				RequestDispatcher view = request.getRequestDispatcher(ADD);
				view.forward(request, response);
			   
			}
		
	    }
	
	
	
}	


