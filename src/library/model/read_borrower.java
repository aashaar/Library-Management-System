package library.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

import library.util.*;

public class read_borrower {
	
	private Connection connection;

    public read_borrower() {
        connection = DbUtil.getConnection();
    }
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
            
            for (int j = 1; j < sheet.getRows(); j++) {
               // for (int i = 0; i < sheet.getRows(); i++) {
            	
                    Cell cell = sheet.getCell(1, j);
                    String ssn = cell.getContents();
                    cell = sheet.getCell(2,j);
                    String fname = cell.getContents();
                    cell = sheet.getCell(3,j);
                    String lname = cell.getContents();
                    cell = sheet.getCell(4,j);
                    String email = cell.getContents();
                    cell = sheet.getCell(8,j);
                    String address = cell.getContents();
                    cell = sheet.getCell(9,j);
                    String phone = cell.getContents();
                    
                    
                    System.out.print(ssn+"-"+fname+"-"+lname+"-"+email+"-"+address+"-"+phone);
                    System.out.println("");
                    
                    try {
                    	
                     // Parameters are starting with 1
                        Statement st = connection.createStatement();
                        int i=0;
                        
                        i=st.executeUpdate("insert into borrower (ssn,fname,lname,email,address,phone) values ('"+ssn+"','"+fname+"','"+lname+"','"+email+"','"+address+"','"+phone+"')");
                        
                        
                       System.out.println(i);
                       

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
               // }
            	
            }
        } 
        catch (Exception e) {
        	
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) throws IOException {
    	read_borrower test = new read_borrower();
        test.setInputFile("C:/Users/Windows PC/Desktop/borrowers.xls");
        test.read();
    }

}
