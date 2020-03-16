package com.juan.ToDoAppWithHibernate;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 
 * To-Do-List Class that holds the properties and methods of each To-Do-List
 * item.
 *
 */

@Entity
@Table(name = "todolist")
public class ToDoList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int itemId;

	@Column(name = "itemEntry")
	private String itemEntry;

	/**
	 * An empty constructor for when the ToDoList class gets called and we don't
	 * want it to do anything.
	 */
	public ToDoList() {

	}

	/**
	 * ToDoList constructor method for when want to automatically set the itemEntry.
	 * @param itemEntry that is going to be inserted into the list.
	 */
	public ToDoList(String itemEntry) {
		this.itemEntry = itemEntry;
	}

	/**
	 * Getter method for the ItemId
	 * @return ItemId of the item in the list
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Setter method for the ItemID
	 * @param itemId of the item in the list
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Getter method for the ItemEntry
	 * @return itemEntry from the list
	 */
	public String getItemEntry() {
		return itemEntry;
	}

	/**
	 * Setter method for the setItemEntry
	 * @param itemEntry
	 */
	public void setItemEntry(String itemEntry) {
		this.itemEntry = itemEntry;
	}

}
