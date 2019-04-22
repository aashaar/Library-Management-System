package library.controller;
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

public class BookController extends  HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String LIST_BOOKS = "/listbooks.jsp";
    private static String CHECK_OUT = "/checkout.jsp";
    private static String HOME = "/index.html";
    
    private BookDAO dao;
    
    

    public BookController() {
        super();
        dao = new BookDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("getBookByIsbn"))
        {
            forward = CHECK_OUT;
            String isbn = request.getParameter("isbn");
            Book bookbyisbn = dao.getBookByIsbn(isbn);
            if(bookbyisbn == null)
            {
            	request.setAttribute("results", false);
            }
            else
            {
            request.setAttribute("book", bookbyisbn);
            }
        } 
        else if (action.equalsIgnoreCase("search"))
        {// search books in books tuple with the search string provided
            forward = LIST_BOOKS;
            System.out.println("query is :"+request.getParameter("searchString"));
            List<Book> filteredBooks =dao.getBooksBySearchString(request.getParameter("searchString"));
            if(filteredBooks.isEmpty() || filteredBooks == null)
            {
            	request.setAttribute("results", false);     
            }
            else
            {
            request.setAttribute("books", filteredBooks);
            }
        }
        
        else {
         
            
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
   
    }
}