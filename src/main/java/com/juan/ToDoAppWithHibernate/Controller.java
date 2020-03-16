package com.juan.ToDoAppWithHibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * This class is used as an intermediary class in order for the UserInterface to
 * not access ToDoList class directly. Here we call all the Hibernate processes
 * that interact with the other Classes.
 *
 */
public class Controller {

	/**
	 * An empty constructor for when the Controller class gets called and we don't
	 * want it to do anything.
	 */
	public Controller() {

	}

	/**
	 * Method that uses Hibernate to get an item entry into the Database.
	 * 
	 * @param itemEntry of the type String that is going to be entered into the
	 *                  database.
	 */
	public void addListItem(String itemEntry) {
		// Create session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(ToDoList.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		Transaction tx = null;
		try {
			// create ToDoList objects
			ToDoList tempList = new ToDoList(itemEntry);

			// start a transaction
			tx = session.beginTransaction();

			// save the ToDoList object
			System.out.println("\nSaving the Item...");
			session.save(tempList);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("The item has been added to the list!\n");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

	/**
	 * Method that uses Hibernate to print out the database entries.
	 */
	public void viewList() {

		// Create session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(ToDoList.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		Transaction tx = null;

		try {

			// start a transaction
			tx = session.beginTransaction();

			// query ListItems
			List<ToDoList> theList = session.createQuery("from ToDoList ORDER BY id").list();
			
			if (theList.isEmpty()) {
				System.out.println("\nThe to-do list is empty.\n");
			}
			// display the ListItems
			System.out.println("");
			for (Iterator iterator1 = theList.iterator(); iterator1.hasNext();) {
				ToDoList tempList = (ToDoList) iterator1.next();
				System.out.print(tempList.getItemId() + ": " + tempList.getItemEntry() + "\n");
			}
			System.out.println("");
			
			// commit the transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

	/**
	 * Method that uses Hibernate to delete an item in the database.
	 * @param itemId of the item that is going to get deleted in the database.
	 */
	public void deleteItem(int itemId) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(ToDoList.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			int itemID = itemId;

			// start a transaction

			tx = session.beginTransaction();

			// retrieve ListItem based on the id: primary key
			ToDoList myList = session.get(ToDoList.class, itemId);

			// delete ListItem id=itemId
			System.out.println("Deleting item with id=" + itemId + "\n");
			session.createQuery("delete from ToDoList where id=" + itemId).executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("\nThe item has been deleted from the list!\n");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
