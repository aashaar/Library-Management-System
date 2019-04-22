package library.model;

import java.io.File;
import java.io.IOException;
import java.util.*;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.sql.*;
import javax.sql.*;

public class read_excel {

    private String inputFile;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void read() throws IOException  {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        Book bk = new Book();
        Map<String, Integer> dictionary_authors = new HashMap<String, Integer>();
        int authorId = 1;
        try {
        
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
            
                for (int i = 0; i < sheet.getRows(); i++) {
                	
                    Cell cell = sheet.getCell(3, i);
                    String authors = cell.getContents().toString();
                    String[] author_array = new String[3];
                    author_array= authors.split(",");
                    
                    for (int m = 0; m < author_array.length; m++) {
                       if(author_array[m].equals("C")) {
                    		System.out.println("Sds");
                    	}
                    	
                        if(dictionary_authors.containsKey(author_array[m]))
                        {
                        	
                        }
                        else
                        {
                        	dictionary_authors.put(author_array[m], authorId);
                        	authorId++;
                        }
                        
                        
                        
                     }
                    
                }
                try {
                Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1234");
				Statement st = con.createStatement();
				int l = 0;
                
                
                for (String name: dictionary_authors.keySet()){

                    String key =name.toString();
                    int value = dictionary_authors.get(name);  
                    System.out.println(value + ", " + key);
                    
                    l = st.executeUpdate("insert into authors values ('"+value+"','"+key+"')");
                    
                    
                } 
                System.out.println(l);
                }catch(Exception e) {System.out.println(e);}
                
          
        } catch (BiffException e) {
        	
            e.printStackTrace();
        }
        
        try {
        	System.out.println("**********************************");
        	w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
           try {
                Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1234");
				Statement st = con.createStatement();
				int g = 0;
            for (int i = 0; i < sheet.getRows(); i++) {
            	
                Cell cell = sheet.getCell(3, i);
                String authors = cell.getContents().toString();
                String[] author_array = new String[3];
                author_array= authors.split(",");
                Cell cell1 = sheet.getCell(0, i);
                String isbn = cell1.getContents().toString();
                String isbn10=("0000000000" + isbn).substring(isbn.length());
                for (int m = 0; m < author_array.length; m++) {
                int auth_id = dictionary_authors.get(author_array[m]);
                System.out.println(auth_id+ "," + isbn10);
                g = st.executeUpdate("insert ignore into book_authors values ('"+auth_id+"','"+isbn10+"')");
                
                }
        	}
            System.out.println("Completed ! "+ g);
        }
            catch(Exception e) {System.out.println(e);}
            
        }
        catch(Exception e)
        {
        System.out.println(e);
        }
        
    }

    public static void main(String[] args) throws IOException {
        read_excel test = new read_excel();
        test.setInputFile("C:/Users/Windows PC/Desktop/books.xls");
        test.read();
    }

}