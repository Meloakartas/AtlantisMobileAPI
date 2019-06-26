package restapi.controller;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam(name = "id_token") String id_token, HttpServletResponse response)
    {
        if(JWTHelper.isUserAuthenticated(id_token))
        {
            Cookie cookie = new Cookie("id_token", id_token);
            response.addCookie(cookie);

            Map<String, Claim> claims = Objects.requireNonNull(JWTHelper.ParseJWT(id_token)).getClaims();

            User user = userService.findUserByUserADid(claims.get("oid").asString());
            if(user == null)
                user = CreateUser(claims);

            return new ResponseEntity<>(user,
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new AuthError("Client not logged in."),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response)
    {
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

        userService.saveOrUpdateUser(userToInsert);
        return userToInsert;
    }
}