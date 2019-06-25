package restapi.controller;

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

    @RequestMapping("/user")
    public User user(@RequestParam(value="userID", defaultValue="0") long userID) {
        return userService.findUserById(userID);
    }

    @RequestMapping("/userDevices")
    public List<Device> userDevices(@RequestParam(value="userID", defaultValue="0") long userID) {
        return userService.findUserById(userID).getUserDevices();
    }
}