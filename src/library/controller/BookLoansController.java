package library.controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.dao.BookDAO;
import library.model.Book;
import library.model.BookLoans;
import library.dao.BookLoansDAO;

public class BookLoansController extends  HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String CHECK_IN_LIST_BOOKS = "/checkinListBooks.jsp";
    private static String CHECK_IN = "/checkin.jsp";
    private BookLoansDAO dao;
    
    
    public BookLoansController() {
        super();
        dao = new BookLoansDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("checkinSearch"))
        { // search books in book_loans tuple with the search string provided
        	String checkinSearchString = request.getParameter("checkinSearchString");
            forward = CHECK_IN_LIST_BOOKS;
            System.out.println("query is :"+request.getParameter("checkinSearchString"));
            List<BookLoans> checkin_filteredBooks =dao.getBooksforCheckIn(checkinSearchString);
            request.setAttribute("checkinBooks", checkin_filteredBooks);
            
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException    
	{
    	System.out.println("bookloanscontroller flag");
    	String action = request.getParameter("action");
    	if(action.equalsIgnoreCase("checkin"))
    	{
    		
    		int i = 0;
    		String loan_id_s = request.getParameter("loan_id");
    		System.out.println("LoanID "+loan_id_s);
    		int loan_id = Integer.parseInt(loan_id_s);
    		System.out.println("Loan id for checking is " + loan_id);
    		i = dao.checkinBook(loan_id);
    		if(i == 1)
    		{
    			request.setAttribute("checkin", true);
    		}
    		else
    		{
    			request.setAttribute("checkin", false);
    		}
    		RequestDispatcher view = request.getRequestDispatcher(CHECK_IN);
			view.forward(request, response);
    	}
	}
}
