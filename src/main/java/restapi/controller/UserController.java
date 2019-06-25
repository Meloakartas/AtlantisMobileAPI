package restapi.controller;

import org.springframework.web.bind.annotation.*;
import restapi.model.Device;
import restapi.model.User;
import restapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/user")
    public User user(@RequestParam(value="userID", defaultValue="0") long userID) {
        return userService.findUserById(userID);
    }

    @GetMapping(value = "/userDevices")
    public List<Device> userDevices(@RequestParam(value="userID", defaultValue="0") long userID) {
        return userService.findUserById(userID).getUserDevices();
    }
}