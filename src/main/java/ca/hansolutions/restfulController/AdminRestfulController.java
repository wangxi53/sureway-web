package ca.hansolutions.restfulController;

import ca.hansolutions.model.Admin;
import ca.hansolutions.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */

@RestController
@RequestMapping(value = "/rest/admins")
@Profile("develop")
public class AdminRestfulController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody Admin admin){
        admin = adminService.addAdmin(admin.getEmail(), admin.getFirstName(), admin.getLastName());

        return admin;
    }

    @RequestMapping(value = "/{adminKey}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable String adminKey){

        adminService.deleteAdmin(adminKey);
    }

    @RequestMapping(value = "/{adminKey}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Admin getAdmin(@PathVariable String adminKey){

        Admin admin = adminService.getAdmin(adminKey);

        return admin;
    }

    @RequestMapping(value = "/{adminKey}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Admin updateAdmin(@RequestBody Admin admin){

        admin = adminService.updateAdmin(admin.getKey().toString(), admin.getEmail(),
                admin.getFirstName(), admin.getLastName());

        return admin;
    }


}
