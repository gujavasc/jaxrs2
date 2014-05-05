package org.gujavasc.resources;

import java.util.concurrent.Executors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.gujavasc.entity.Book;
import org.gujavasc.repository.Repository;

@Path("async/books")
public class AsyncBookResource extends Repository<Book> {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void longRunningOperation(@Suspended final AsyncResponse asyncResponse) {
        Runnable command = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                asyncResponse.resume(new Book());
            }
        };

        Executors.newSingleThreadExecutor().execute(command);
    }

}
