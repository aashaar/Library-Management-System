package library.controller;

import library.model.Fine;

import library.dao.FineDAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FineController extends  HttpServlet {
	
private static String HOME = "/index.html";
private static String PAY_FINE = "/payFine.jsp";
private static String VIEW_BORROWER_TOTAL_FINE = "/viewBorrowerTotalFine.jsp";
private static String REFRESHED_BORROWER_TOTAL_FINE = "/FineController?action=refreshFine";
	
	
	private FineDAO dao;
 
    public FineController() {
        super();
        dao = new FineDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	String forward="";
	    String action = request.getParameter("action");
    	
    	if (action.equalsIgnoreCase("refreshFine"))
    	{
    		forward="/FineController?action=viewfine";
	    	System.out.println(forward);
	    	dao.refreshFine();
	    	request.setAttribute("finesRefreshed", true);
	    }
    	else if(action.equalsIgnoreCase("viewfine"))
    	{
    		forward = VIEW_BORROWER_TOTAL_FINE;
    		//TODO: display all borrowers who have checked out with their fines
    		//TODO: we need card id, borrower fname & lname and fine amount.
    		List <Fine> borrowerTotalFines = dao.viewBorrowerFines();
    		request.setAttribute("borrowerTotalFines", borrowerTotalFines);		
    	}
    	else if (action.equalsIgnoreCase("searchforpayfine"))
    	{
    		//TODO: Display all books which are checked in and give and option to pay fines for them.
    		//TODO: we need loan id, card_id, isbn, borrower fname & lname, checked_in date and fine amount
    		forward =PAY_FINE;
    		//TODO: call dao function
    		List<Fine> fines = dao.getBooksToPayFine();
            request.setAttribute("fines", fines);
    	}
    	else if(action.equalsIgnoreCase("payfine"))
    	{
    		forward = REFRESHED_BORROWER_TOTAL_FINE;
    		int loan_id = Integer.parseInt(request.getParameter("loan_id"));
    		int return_i = 0;
        	return_i = dao.payFine(loan_id);
        	if (return_i ==1)
        	{
        		request.setAttribute("finePaid", true);
        	}
        	else
        	{
        		request.setAttribute("finePaid", false);
        	}
    	}
    	
    	System.out.println(forward);
	    RequestDispatcher view = request.getRequestDispatcher(forward);
	    view.forward(request, response);
    	
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	
    }

}
