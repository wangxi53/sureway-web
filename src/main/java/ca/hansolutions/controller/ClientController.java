package ca.hansolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */

@Controller
@RequestMapping(value = "/clients")
public class ClientController {

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addClients(Map<String, Object> map){

        return "";
    }

    @RequestMapping(value = "/delete/{clientkey}", method = RequestMethod.POST)
    public String deleteClients(Map<String, Object> map){

        return "";
    }

    @RequestMapping(value = "/{clientKey}", method = RequestMethod.GET)
    public String getClients(Map<String, Object> map){

        return "";
    }

    @RequestMapping(value = "/{clientKey}", method = RequestMethod.POST)
    public String updateClients(Map<String, Object> map){


        return "";
    }


}
