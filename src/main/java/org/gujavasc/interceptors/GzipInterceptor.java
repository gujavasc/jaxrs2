package org.gujavasc.interceptors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.*;
import java.io.IOException;

/**
 * @author <a href="mailto:danielsoro@gmail.com">Daniel Cunha (soro)</a>
 */
@Provider
public class GzipInterceptor implements ReaderInterceptor, WriterInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        System.out.println(context);
        return null;
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        System.out.println(context);
    }
}
