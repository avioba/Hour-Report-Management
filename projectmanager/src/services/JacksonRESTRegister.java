package services;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JacksonRESTRegister extends ResourceConfig {
    public JacksonRESTRegister() {
        packages("services");
        register(JacksonFeature.class);
    }
}