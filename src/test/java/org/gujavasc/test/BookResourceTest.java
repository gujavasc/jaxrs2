package org.gujavasc.test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.gujavasc.entity.Book;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BookResourceTest {

    @Test
    public void javaee6() throws Exception {
        URL url = new URL("http://localhost:8080/rest-example/resources/books");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String result = IOUtils.toString(con.getInputStream(), "UTF-8");
        con.disconnect();

        List<Book> books = new Gson().fromJson(result, new TypeToken<Collection<Book>>() {}.getType());

        assertThat(books, not(empty()));
    }

    //@formatter:off
    @Test
    public void javaee7() throws Exception {
        Client client = ClientBuilder.newClient();
      
        List<Book> books = client.target("http://localhost:8080/rest-example/resources")
                                 .path("books")
                                 .request(MediaType.APPLICATION_JSON)
                                 .get(new GenericType<List<Book>>(){});
        
        assertThat(books, not(empty()));
    }
    //@formatter:on

}
