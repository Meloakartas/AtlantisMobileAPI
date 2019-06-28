package restapi.controller;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import restapi.model.AuthError;
import restapi.model.User;
import restapi.service.IUserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Controller
public class AuthController {

    private final IUserService userService;

    private final IJWTHelper jwthelper;

    public AuthController(IUserService userService, IJWTHelper jwthelper) {
        this.userService = userService;
        this.jwthelper = jwthelper;
    }

    @CrossOrigin()
    @RequestMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam(name = "id_token") String id_token, HttpServletResponse response)
    {
        System.out.println("Call on Validate with : " + id_token);
        if(jwthelper.isUserAuthenticated(id_token))
        {
            System.out.println("User is connected");
            Cookie cookie = new Cookie("id_token", id_token);
            response.addCookie(cookie);

            Map<String, Claim> claims = Objects.requireNonNull(jwthelper.ParseJWT(id_token)).getClaims();

            User user = userService.findUserByUserADid(claims.get("oid").asString());
            if(user == null)
                user = CreateUser(claims);

            return new ResponseEntity<>(user,
                    HttpStatus.OK);
        }
        else
        {
            System.out.println("User is not connected");
            return new ResponseEntity<>(new AuthError("Client not logged in."),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response)
    {
        System.out.println("Logging out...");
        Cookie cookie = new Cookie("id_token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntity<>(new AuthError("Successfully logged out !"),
                HttpStatus.OK);
    }

    private User CreateUser(Map<String, Claim> claims)
    {
        User userToInsert = new User(
                claims.get("oid").asString(),
                claims.get("given_name").asString().substring(0, 1).toUpperCase() + claims.get("given_name").asString().substring(1),
                claims.get("family_name").asString().substring(0, 1).toUpperCase() + claims.get("family_name").asString().substring(1)
        );

        return userService.saveOrUpdateUser(userToInsert);
    }
}