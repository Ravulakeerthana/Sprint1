# Sprint1
FIRST JAVA PROJECT (Bookstore  Database Management System).


          JAVA PROJECT (Bookstore Database Management System).
         <---------------------------------------------------->
         
package com.anp.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "book")
public class BookStore {
	
	
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int bookid;
    
	private String bookname;
	
	@Column (name = "book _author")
	private String authorname;
	
	private double price;
	
	private int stock;
	
	
	
	
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
	@Override
	public String toString() {
		return "BookStore [bookid=" + bookid + ", bookname=" + bookname + ", authorname=" + authorname + ", price="
				+ price + ", stock=" + stock + "]";
	}
	
	
	
	public BookStore(int bookid,String bookname,String authorname,double price,int stock) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		this.authorname = authorname;
		this.price = price;
		this.stock = stock;  
		
		
	}
	
	public BookStore() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
}

<--------------------------------------------------------------------->

package com.anp.project;

import java.util.List;
import java.util.Optional;

//import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BookStoreDAO {
	
	private EntityManager em;

	public BookStoreDAO(final EntityManager em) {

		this.em = em;
}
	
	public void createBookStore(final BookStore Book) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction(); // Return the resource-level EntityTransaction object

			if (!tx.isActive()) // Indicate whether a resource transaction is in progress.
			{
				tx.begin();
			}
			
		BookStore mergedBookStore = em.merge(Book);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		public void UpdateBookStore( int id, String newbookname, String newauthorname, double newprice, int newstock) {
			EntityTransaction tx = null; 
			try {
			tx = em.getTransaction(); // Return the resource-level EntityTransaction object

			if (!tx.isActive()) // Indicate whether a resource transaction is in progress.
			{
				tx.begin();
			}
			BookStore b1 = em.find(BookStore.class, id) ;
			if(b1 !=null) {
				b1.setBookname(newbookname);
				b1.setAuthorname(newauthorname);
				b1.setPrice(newprice);
				b1.setStock(newstock);
			
				
				em.merge(b1); //merger the changes
				tx.commit(); 
			}
			  
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
		public Optional<BookStore> getById(int id) {

			BookStore t = em.find(BookStore.class, id);

			if (t != null) {
			return Optional.of(t);
		} else {
			return Optional.empty();
		}
	}	
		public List<BookStore> getAll() {

			List<BookStore> t1 = em.createQuery("from BookStore", BookStore.class).getResultList();

			return t1;
		}
			
		public void removeById(int id) {  //removing  based on id 
			
			EntityTransaction tx = null; 
			
			BookStore tr = em.find(BookStore.class, id);  
			
			try {
				
				tx = em.getTransaction(); // Return the resource-level EntityTransaction object

				if (!tx.isActive()) // Indicate whether a resource transaction is in progress.
				{
					tx.begin();
					}
					em.remove(tr);
					tx.commit();
				}
			
				catch (Exception e) {
					 e.printStackTrace();
				}	
			}
		
		
}

<--------------------------------------------------------------------->

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


<======================================================================>

// OUTPUT :  

Dec 03, 2023 5:05:06 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: ks]
Dec 03, 2023 5:05:06 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.3.1.Final
Dec 03, 2023 5:05:06 PM org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl lambda$normalizeConnectionAccessUserAndPass$6
WARN: HHH90000021: Encountered deprecated setting [javax.persistence.jdbc.user], use [jakarta.persistence.jdbc.user] instead
Dec 03, 2023 5:05:06 PM org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl lambda$normalizeConnectionAccessUserAndPass$12
WARN: HHH90000021: Encountered deprecated setting [javax.persistence.jdbc.password], use [jakarta.persistence.jdbc.password] instead
Dec 03, 2023 5:05:06 PM org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl normalizeDataAccess
WARN: HHH90000021: Encountered deprecated setting [javax.persistence.jdbc.url], use [jakarta.persistence.jdbc.url] instead
Dec 03, 2023 5:05:06 PM org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl normalizeDataAccess
WARN: HHH90000021: Encountered deprecated setting [javax.persistence.jdbc.driver], use [jakarta.persistence.jdbc.driver] instead
Dec 03, 2023 5:05:06 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
Dec 03, 2023 5:05:06 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
Dec 03, 2023 5:05:06 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
Dec 03, 2023 5:05:06 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3306/bookcollection]
Dec 03, 2023 5:05:06 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=root}
Dec 03, 2023 5:05:06 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
Dec 03, 2023 5:05:06 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
Dec 03, 2023 5:05:08 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Dec 03, 2023 5:05:08 PM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@750f64fe] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
-----WELCOME TO BOOKSTORE DATABASES-----
Hibernate: 
    select
        bs1_0.bookid,
        bs1_0.book _author,
        bs1_0.bookname,
        bs1_0.price,
        bs1_0.stock 
    from
        book bs1_0 
    where
        bs1_0.bookid=?
Hibernate: 
    select
        bs1_0.bookid,
        bs1_0.book _author,
        bs1_0.bookname,
        bs1_0.price,
        bs1_0.stock 
    from
        book bs1_0 
    where
        bs1_0.bookid=?
Hibernate: 
    insert 
    into
        book
        (book _author, bookname, price, stock) 
    values
        (?, ?, ?, ?)
Hibernate: 
    select
        bs1_0.bookid,
        bs1_0.book _author,
        bs1_0.bookname,
        bs1_0.price,
        bs1_0.stock 
    from
        book bs1_0 
    where
        bs1_0.bookid=?
Hibernate: 
    insert 
    into
        book
        (book _author, bookname, price, stock) 
    values
        (?, ?, ?, ?)
Hibernate: 
    select
        bs1_0.bookid,
        bs1_0.book _author,
        bs1_0.bookname,
        bs1_0.price,
        bs1_0.stock 
    from
        book bs1_0 
    where
        bs1_0.bookid=?
Hibernate: 
    insert 
    into
        book
        (book _author, bookname, price, stock) 
    values
        (?, ?, ?, ?)
  Data added successfully  
---------------------------------------------
Hibernate: 
    select
        bs1_0.bookid,
        bs1_0.book _author,
        bs1_0.bookname,
        bs1_0.price,
        bs1_0.stock 
    from
        book bs1_0 
    where
        bs1_0.bookid=?
 Data updated successfully 
-----------------------------------------------
  Book details based on the id  :
Optional[BookStore [bookid=1, bookname=Thinking,fast and slow, authorname=Dainel Kahneman , price=599.0, stock=30]]
  Details of all the bookstore  
Hibernate: 
    select
        bs1_0.bookid,
        bs1_0.book _author,
        bs1_0.bookname,
        bs1_0.price,
        bs1_0.stock 
    from
        book bs1_0
[BookStore [bookid=1, bookname=Thinking,fast and slow, authorname=Dainel Kahneman , price=599.0, stock=30], BookStore [bookid=5, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=6, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=7, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=8, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=9, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=10, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=11, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=12, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=13, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=14, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=15, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=16, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=17, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=18, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=19, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=20, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=21, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=22, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=23, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=24, bookname=Becoming, authorname=Michelle Obama, price=899.0, stock=10], BookStore [bookid=25, bookname=Sapiens, authorname=YualNoh Harai, price=699.0, stock=12], BookStore [bookid=26, bookname=The power of Habit, authorname=Charles Duhigg, price=499.0, stock=25], BookStore [bookid=27, bookname=Becoming, authorname=Michelle Obama, price=899.0, stock=10]]
  Data removes based on id : 
Hibernate: 
    delete 
    from
        book 
    where
        bookid=?
 1st record is removed 
 Data removed successfully  
-----------------------------------------------




