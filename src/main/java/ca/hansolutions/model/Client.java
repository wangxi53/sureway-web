package ca.hansolutions.model;

import com.google.appengine.api.datastore.Entity;
import org.joda.time.DateTime;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */
public class Client extends User {

    public static String KIND = "client";

    public Client(Entity entity) {

        this.key = entity.getKey();
        this.firstName = entity.getProperty(Constants.FIRST_NAME).toString();
        this.lastName = entity.getProperty(Constants.LAST_NAME).toString();
        this.email = entity.getProperty(Constants.EMAIL).toString();
        this.creationTime = new DateTime(entity.getProperty(Constants.CREATION_TIME));
    }
}
