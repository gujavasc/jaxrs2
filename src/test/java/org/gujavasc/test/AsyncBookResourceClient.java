package org.gujavasc.test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;

import org.gujavasc.entity.Book;

public class AsyncBookResourceClient {

    public static void main(String[] args) {
        new AsyncBookResourceClient().buscaOLivroDeFormaAssincrona();
    }

    public void buscaOLivroDeFormaAssincrona() {
        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/rest-example/resources/async/books/");
        // WebTarget target =
        // ClientBuilder.newClient().target("http://localhost:8080/rest-example/resources/ejb/async/books/"); // Para
        // testar o EJB...

        target.request().async().get(new InvocationCallback<Book>() {

            public void completed(Book book) {
                System.out.println("Completed...");
            }

            public void failed(Throwable throwable) {
                System.out.println(throwable);
            }

        });

        System.out.println("Final do metodo principal...");

        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
