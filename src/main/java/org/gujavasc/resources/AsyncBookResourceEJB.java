package org.gujavasc.resources;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.gujavasc.entity.Book;
import org.gujavasc.repository.Repository;

@Stateless
@Path("ejb/async/books")
public class AsyncBookResourceEJB extends Repository<Book> {

    @GET
    @Path("/")
    @Asynchronous
    @Produces(MediaType.APPLICATION_JSON)
    public void longRunningOperation(@Suspended final AsyncResponse asyncResponse) {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        asyncResponse.resume(new Book());
    }

}
