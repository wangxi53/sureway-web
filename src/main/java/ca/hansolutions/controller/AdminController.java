package ca.hansolutions.controller;

import ca.hansolutions.model.Admin;
import ca.hansolutions.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */

@Controller
@RequestMapping(value = "/admins")
@Profile("production")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addAdmin(Map<String,Object> map, @RequestParam String email, @RequestParam String firstName,
                           @RequestParam String lastName){
        Admin admin = adminService.addAdmin(email, firstName, lastName);

        map.put("newAdmin", admin);

        return "mainpage";
    }

    @RequestMapping(value = "/delete/{adminKey}", method = RequestMethod.POST)
    public String deleteAdmin(Map<String,Object> map, @PathVariable String adminKey){
        try{
            adminService.deleteAdmin(adminKey);
            map.put("message", "SuccessFully");
        } catch (Exception e){
            map.put("message", "Failed!");
        }

        return "mainpage";
    }

    @RequestMapping(value = "/{adminKey}", method = RequestMethod.GET)
    public String getAdmin(Map<String,Object> map, @PathVariable String adminKey){
        Admin admin = adminService.getAdmin(adminKey);
        map.put("getAdmin", admin);

        return "mainpage";
    }

    @RequestMapping(value = "/{adminKey}", method = RequestMethod.POST)
    public String updateAdmin(Map<String,Object> map, @PathVariable String adminKey, @RequestParam  String email,
                              @RequestParam String firstName, @RequestParam String lastName){
        Admin admin = adminService.updateAdmin(adminKey, email, firstName, lastName);
        map.put("updateAdmin", admin);

        return "mainpage";
    }

}
