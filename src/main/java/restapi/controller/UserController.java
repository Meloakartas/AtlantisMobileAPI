package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.model.AuthError;
import restapi.model.Device;
import restapi.model.User;
import restapi.service.IUserService;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    private final IUserService userService;

    private final IJWTHelper jwthelper;

    public UserController(IUserService userService, IJWTHelper jwthelper) {
        this.userService = userService;
        this.jwthelper = jwthelper;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> user(@CookieValue(value = "id_token") String id_token) {
        System.out.println("Getting self user");
        if(jwthelper.isUserAuthenticated(id_token))
        {
            String userADid = Objects.requireNonNull(jwthelper.ParseJWT(id_token)).getClaims().get("oid").asString();
            User user = userService.findUserByUserADid(userADid);
            return new ResponseEntity<>(user,
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new AuthError("Unauthorized access."),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/userDevices")
    public ResponseEntity<?> userDevices(@CookieValue(value = "id_token") String id_token) {
        System.out.println("User self devices");
        if(jwthelper.isUserAuthenticated(id_token))
        {
            String userADid = Objects.requireNonNull(jwthelper.ParseJWT(id_token)).getClaims().get("oid").asString();
            List<Device> userDevices = userService.findUserByUserADid(userADid).getUserDevices();
            return new ResponseEntity<>(userDevices,
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new AuthError("Unauthorized access."),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}