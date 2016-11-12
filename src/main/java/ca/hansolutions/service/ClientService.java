package ca.hansolutions.service;

import ca.hansolutions.model.Admin;
import ca.hansolutions.model.Client;
import ca.hansolutions.model.Constants;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */

@Service
public class ClientService {

    @Autowired
    UtilityService utilityService;

    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    public Client addClient(String userId, String email, String firstName, String lastName){

        String clientKey = utilityService.generateKey(Client.KIND);

        Entity client = new Entity(Client.KIND, clientKey);
        client.setProperty(Constants.ADMIN_ID, userId);
        client.setProperty(Constants.FIRST_NAME, firstName);
        client.setProperty(Constants.LAST_NAME, lastName);
        client.setProperty(Constants.EMAIL, email);
        client.setProperty(Constants.CREATION_TIME, new DateTime().toString());

        datastoreService.put(client);

        return new Client(client);
    }

    public void deleteClient(String keyString){
        Key key = utilityService.getKeyFromKeyString(Client.KIND, keyString);
        datastoreService.delete(key);
    }

    public Client getClient(String keyString){
        Entity client = utilityService.getEntityByKeyString(Client.KIND, keyString);

        return new Client(client);
    }

    public Client updateClient(String keyString, String userId, String email, String firstName, String lastName){
        Entity client = utilityService.getEntityByKeyString(Admin.KIND, keyString);

        client.setProperty(Constants.CLIENT_ID, userId);
        client.setProperty(Constants.FIRST_NAME, firstName);
        client.setProperty(Constants.LAST_NAME, lastName);
        client.setProperty(Constants.EMAIL, email);

        datastoreService.put(client);

        return new Client(client);
    }

}
