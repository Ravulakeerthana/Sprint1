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
	