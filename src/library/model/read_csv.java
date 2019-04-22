package library.model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.sql.*;

public class read_csv {

	public static void main(String args[])
	{
		
		// Reading the csv file		
		//Create List for Books
				List<Book> books_list = new ArrayList <Book>();
				
			String row = "";
			BufferedReader br = null;
			try {
			//br = new BufferedReader (new FileReader("F:\\UTD\\1st Sem\\Database Design CS 6360\\Project 01\\books.csv"));
		//	String csvfile = "F:/UTD/1st Sem/Database Design CS 6360/Project 01/books.csv";
			String csvfile = "C:/Users/Windows PC/Desktop/books.csv";
			br = new BufferedReader (new FileReader(csvfile));
		
		
		//Reading header column of csv separately to prevent it from entering the list
		
			br.readLine();
		
		//Starting reading from 2nd line of csv
		
			while ((row = br.readLine()) != null)
			{
				String [] book_data = row.split(",");
				
				if(book_data.length > 0)
				{
					// change class remove authors and uncomment the following two lines
				//	Book books1 = new Book (book_data[0],book_data[2]);
				//	books_list.add(books1);
				}
			}
		
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
			System.out.println(books_list.size());
		//Display the list on console
			for (Book b : books_list)
			{
				String isbn = b.getIsbn();
				String isbn10=("0000000000" + isbn).substring(isbn.length());

				System.out.println(isbn10+" - "+b.getTitle());
				
			}
		
		
		// Insert into database
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1234");
				Statement st = con.createStatement();
				int i = 0;
				for (Book b : books_list)
				{
					String isbn = b.getIsbn().toString();
					String title = b.getTitle().toString();
					String isbn10=("0000000000" + isbn).substring(isbn.length());

					i = st.executeUpdate("insert into books values ('"+isbn10+"','"+title+"')");
				}
				System.out.println("Completed ! "+ i);
			}
			catch (Exception e1)
			{
				System.out.println(e1);
			}

		
		
	}
}
