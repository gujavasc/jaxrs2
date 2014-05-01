package org.gujava.repository;

import java.util.ArrayList;
import java.util.List;

import org.gujavasc.entity.Book;

public class Repository <T>{
	
	protected List<Book> getList(){
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1, "Book 1", "Description1..."));
		books.add(new Book(2, "Book 2", "Description2..."));
		books.add(new Book(3, "Book 3", "Description3..."));
		return books;
	}
	
	protected Book createObject(Book book){
		return book;
	}
	
	protected Book readObject(Integer id){
		Book book = new Book(1, "Book 1", "Description1...");
		return book;
	}
	
	protected Book updateObject(Book book){
		return book;
	}
	
	protected boolean deleteObject(Book book){
		return true;
	}
}
