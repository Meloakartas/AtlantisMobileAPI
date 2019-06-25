package restapi.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import restapi.model.Device;
import restapi.model.User;
import restapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User user(@RequestParam(value="userID", defaultValue="0") long userID) {
        return userService.findUserById(userID);
    }

    @RequestMapping(value = "/userDevices", method = RequestMethod.GET)
    public List<Device> userDevices(@RequestParam(value="userID", defaultValue="0") long userID) {
        return userService.findUserById(userID).getUserDevices();
    }
}