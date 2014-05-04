package org.gujavasc.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.gujavasc.entity.Book;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BookResourceTest {

    @Test
    public void deveConterLivros_javaee6() throws Exception {
        URL url = new URL("http://localhost:8080/rest-example/resources/books");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String result = IOUtils.toString(con.getInputStream(), "UTF-8");
        con.disconnect();

        Type returnType = new TypeToken<Collection<Book>>() {}.getType();
        List<Book> books = new Gson().fromJson(result, returnType);

        assertThat(books, hasSize(3));
    }

    //@formatter:off
    @Test
    public void deveConterLivros_javaee7() {
        Client client = ClientBuilder.newClient();
      
        List<Book> books = client.target("http://localhost:8080/rest-example/resources")
                                 .path("books")
                                 .request(MediaType.APPLICATION_JSON)
                                 .get(new GenericType<List<Book>>(){});
        
        assertThat(books, hasSize(3));
    }
    //@formatter:on

    @Test
    public void deveConterOLivro_javaee6() throws Exception {
        URL url = new URL("http://localhost:8080/rest-example/resources/books/10");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String result = IOUtils.toString(con.getInputStream(), "UTF-8");
        con.disconnect();

        Book book = new Gson().fromJson(result, Book.class);

        assertThat(book, notNullValue());
    }

    //@formatter:off
    @Test
    public void deveConterOLivro_javaee7() {
        Client client = ClientBuilder.newClient();
        
        WebTarget target = client.target("http://localhost:8080/rest-example/resources");
        WebTarget path = target.path("books/{id}");
        WebTarget bookId = path.resolveTemplate("id", "10");
        
        Builder invocation = bookId.request();
        Book book = invocation.get(Book.class);
        
        assertThat(book, notNullValue());
    }
    //@formatter:on

    //@formatter:off
    @Test
    public void deveConterLivrosAndroidAno2014_javaee7() {
        Client client = ClientBuilder.newClient();
        
        WebTarget base = client.target("http://localhost:8080/rest-example/resources");
        WebTarget books = base.path("books");
        WebTarget filter = books.path("filter");
        WebTarget year = filter.path("{year}");
        WebTarget templateOk = year.resolveTemplate("year", "2014");
        WebTarget withParam = templateOk.queryParam("name", "Android");
        
        Builder request = withParam.request(MediaType.APPLICATION_JSON);
        List<Book> booksResult = request.get(new GenericType<List<Book>>(){});
        
        assertThat(booksResult, hasSize(2));
    }
    //@formatter:on

    //@formatter:off
    @Test
    public void deletarOLivro_javaee7() {
        Client client = ClientBuilder.newClient();
        
        Response response = client.target("http://localhost:8080/rest-example/resources")
                                  .path("books")
                                  .path("{id}")
                                  .resolveTemplate("id", "10")
                                  .request()
                                  .delete();
        
        response.close();
        
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }
    //@formatter:on

    //@formatter:off
    @Test
    public void inserirOLivro_javaee7() {
        Client client = ClientBuilder.newClient();
        
        Book book = new Book(1, "Android", "Direto das trincheiras");
        
        Response response = client.target("http://localhost:8080/rest-example/resources")
                                  .path("books")
                                  .request(MediaType.APPLICATION_JSON)
                                  .post(Entity.entity(book, MediaType.APPLICATION_JSON));
        
        response.close();
        
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }
    //@formatter:on

    //@formatter:off
    @Test
    public void inserirOLivroComFiltroDeLog_javaee7() {
        Client client = ClientBuilder.newClient().register(MyLoggingFilter.class);
        
        Book book = new Book(1, "Android", "Direto das trincheiras");
        
        Response response = client.target("http://localhost:8080/rest-example/resources")
                                  .path("books")
                                  .register(MyEntityInterceptor.class)
                                  .request(MediaType.APPLICATION_JSON)
                                  .post(Entity.entity(book, MediaType.APPLICATION_JSON));
        
        response.close();
        
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }
    //@formatter:on

}
