package org.gujavasc.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.gujavasc.entity.Book;
import org.gujavasc.repository.Repository;

@Path("books")
public class BookResource extends Repository<Book>{
	
	@GET
	@Path("/filter/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listByYearAndName(@PathParam("year") Integer year, @QueryParam("name") String name){
		List<Book> books = getListByYearAndName(year, name);
		ResponseBuilder rb = Response.ok(books);
		return rb.build();
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(){
		ResponseBuilder rb = Response.ok(getList());
		return rb.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Book book){
		ResponseBuilder rb = Response.ok(createObject(book));
		return rb.build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") Integer id, @Context UriInfo uriInfo){
		System.out.println(uriInfo.getBaseUri());
		Link listLink = Link.fromPath(uriInfo.getBaseUri() + "books")
				.rel("list")
				.build();
		return Response.ok(readObject(id))
				.links(listLink)
				.link(uriInfo.getBaseUri() + "purchase", "purchase")
				.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Book book){
		ResponseBuilder rb = Response.ok(book);
		return rb.build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer id){
		return Response.ok().build();
	}
}
