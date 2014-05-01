package org.gujavasc.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.gujavasc.entity.Book;

@Path("books")
public class BookResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks(){
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1, "Book 1", "Description1..."));
		books.add(new Book(2, "Book 2", "Description2..."));
		books.add(new Book(3, "Book 3", "Description3..."));
		ResponseBuilder rb = Response.ok(books);
		return rb.build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("id") Integer id){
		Book book = new Book(1, "Book 1", "Description1...");
		ResponseBuilder rb = Response.ok(book);
		return rb.build();
	}
	
	@POST
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@PathParam("id") Integer id){
		Book book = new Book(1, "Book 1", "Description1...");
		ResponseBuilder rb = Response.ok(book);
		return rb.build();
	}
}
