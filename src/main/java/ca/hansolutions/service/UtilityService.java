package ca.hansolutions.service;

import com.google.appengine.api.datastore.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */

@Service
public class UtilityService {

    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    public String generateKey(String kind){

        UUID uniqueKey = UUID.randomUUID();

        return kind + uniqueKey;
    }

    public Key getKeyFromKeyString(String kind, String keyString){

        return KeyFactory.createKey(kind, keyString);
    }

    public Entity getEntityByKeyString(String kind, String keyString) {

        Key key = getKeyFromKeyString(kind, keyString);

        Entity entity = null;
        try {
            entity = datastoreService.get(key);
        } catch (EntityNotFoundException e) {
            return null;
        }

        return entity;
    }

}
