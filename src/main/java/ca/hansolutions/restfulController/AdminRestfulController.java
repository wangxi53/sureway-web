package ca.hansolutions.restfulController;

import ca.hansolutions.model.Admin;
import ca.hansolutions.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */

@Controller
@RequestMapping(value = "/rest/admins")
public class AdminRestfulController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Admin addAdmin(@RequestParam  String email, @RequestParam String firstName,
                          @RequestParam String lastName){
        Admin admin = adminService.addAdmin(email, firstName, lastName);

        return admin;
    }

    @RequestMapping(value = "/delete/{adminKey}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAdmin(@PathVariable String adminKey){

        adminService.deleteAdmin(adminKey);
    }

    @RequestMapping(value = "/{adminKey}", method = RequestMethod.GET)
    public Admin getAdmin(@PathVariable String adminKey){

        Admin admin = adminService.getAdmin(adminKey);

        return admin;
    }

    @RequestMapping(value = "/{adminKey}", method = RequestMethod.POST)
    public Admin updateAdmin(@PathVariable String adminKey, @RequestParam  String email,
                             @RequestParam String firstName, @RequestParam String lastName){

        Admin admin = adminService.updateAdmin(adminKey, email, firstName, lastName);

        return admin;
    }


}
