package org.gujavasc.test;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class MyLoggingFilter implements ClientRequestFilter, ClientResponseFilter {

    // implement the ClientRequestFilter.filter method
    public void filter(ClientRequestContext requestContext) throws IOException {
        System.out.println(requestContext.getUri());
    }

    // implement the ClientResponseFilter.filter method
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        System.out.println(responseContext.getStatus());
    }

}
