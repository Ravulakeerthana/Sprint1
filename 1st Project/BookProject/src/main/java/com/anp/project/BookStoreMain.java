package com.anp.project;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class BookStoreMain {

			public static void main(String []a) {
			EntityManagerFactory factory = null;
				
		   try { 
			//connecting to database using persistence unit
					factory  = Persistence.createEntityManagerFactory("ks");
					EntityManager em = factory.createEntityManager();
					
					
		System.out.println("-----WELCOME TO BOOKSTORE DATABASES-----");
		  
		  
		  BookStore book1 = new BookStore(1, "Thinking,fast and slow" , "Dainel Kahneman ", 599.0 , 30);
		  BookStore	book2 = new BookStore(2, "Sapiens" , "YualNoh Harai", 699.0 , 12);
		  BookStore book3 = new BookStore(3, "The power of Habit" , "Charles Duhigg" ,  499.0 , 25);
		  BookStore book4 = new BookStore(4, "Becoming" , "Michelle Obama" , 899.0 , 10 );

					

		  BookStoreDAO bDAO = new BookStoreDAO(em);
		  
		  bDAO.createBookStore(book1);
		  bDAO.createBookStore(book2);
		  bDAO.createBookStore(book3);
		  bDAO.createBookStore(book4);
					
		    System.out.println("  Data added successfully  ");
					
	System.out.println("---------------------------------------------");

					int  newid = 3;
					String newbookname ="The Lean Startup" ;
					String newauthorname = "Eric Ries" ;
					double newprice = 999.0;
					int newstock = 45;
			
					
			bDAO.UpdateBookStore(newid, newbookname, newauthorname, newprice, newstock);
					
			
			System.out.println(" Data updated successfully ");
			
	     System.out.println("-----------------------------------------------");	
			
			System.out.println("  Book details based on the id  :");
							
							Optional<BookStore> bk1 = bDAO.getById(1);
							System.out.println(bk1);
							 
							bDAO.getById(1);
							
		       System.out.println("  Details of all the bookstore  ");	
							
							List<BookStore> allbk = bDAO.getAll();
			                System.out.println(allbk);
							
							System.out.println("  Data removes based on id : ");
							
							bDAO.removeById(1);
							
							System.out.println(" 1st record is removed ");

							
			System.out.println(" Data removed successfully  ");
			
		 System.out.println("-----------------------------------------------");	

			
						}
		   
				catch (HibernateException e) {
							 e.printStackTrace();
			//				 System.out.println(" Hibernate exception ");
						}
				catch (Exception e) {
			//			 e.printStackTrace();
						 System.out.println(" Exception ");
						}

		        	}
		
	
}

