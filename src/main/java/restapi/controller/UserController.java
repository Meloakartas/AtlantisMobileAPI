package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.model.Device;
import restapi.model.User;
import restapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> user(@CookieValue(value = "id_token") String id_token) {
        if(JWTHelper.isUserAuthenticated(id_token))
        {
            String userADid = Objects.requireNonNull(JWTHelper.ParseJWT(id_token)).getClaims().get("oid").asString();
            User user = userService.findUserByUserADid(userADid);
            return new ResponseEntity<>(user,
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Unauthorized access.",
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/userDevices")
    public ResponseEntity<?> userDevices(@CookieValue(value = "id_token") String id_token) {
        if(JWTHelper.isUserAuthenticated(id_token))
        {
            String userADid = Objects.requireNonNull(JWTHelper.ParseJWT(id_token)).getClaims().get("oid").asString();
            List<Device> userDevices = userService.findUserByUserADid(userADid).getUserDevices();
            return new ResponseEntity<>(userDevices,
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Unauthorized access.",
                    HttpStatus.UNAUTHORIZED);
        }
    }
}