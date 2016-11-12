package ca.hansolutions.service;

import ca.hansolutions.model.Admin;
import ca.hansolutions.model.Constants;
import com.google.appengine.api.datastore.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */

@Service
public class AdminService {

    @Autowired
    UtilityService utilityService;

    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    public Admin addAdmin(String email, String firstName, String lastName){

        String adminKey = utilityService.generateKey(Admin.KIND);

        Entity admin = new Entity(Admin.KIND, adminKey);
        admin.setProperty(Constants.FIRST_NAME, firstName);
        admin.setProperty(Constants.LAST_NAME, lastName);
        admin.setProperty(Constants.EMAIL, email);
        admin.setProperty(Constants.CREATION_TIME, new DateTime().toString());

        datastoreService.put(admin);

        return new Admin(admin);
    }

    public void deleteAdmin(String adminKeyString){
        Key key = utilityService.getKeyFromKeyString(Admin.KIND, adminKeyString);
        datastoreService.delete(key);
    }

    public Admin getAdmin(String adminKeyString){
        Entity admin = utilityService.getEntityByKeyString(Admin.KIND, adminKeyString);

        return new Admin(admin);
    }

    public Admin updateAdmin(String adminKeyString, String email, String firstName, String lastName){
        Entity admin = utilityService.getEntityByKeyString(Admin.KIND, adminKeyString);

        admin.setProperty(Constants.FIRST_NAME, firstName);
        admin.setProperty(Constants.LAST_NAME, lastName);
        admin.setProperty(Constants.EMAIL, email);

        datastoreService.put(admin);

        return new Admin(admin);
    }

}
