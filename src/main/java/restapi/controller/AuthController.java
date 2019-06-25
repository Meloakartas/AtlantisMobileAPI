package restapi.controller;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name = "id_token") String id_token, HttpServletResponse response)
    {
        if(JWTHelper.isUserAuthenticated(id_token))
        {
            Cookie cookie = new Cookie("id_token", id_token);
            response.addCookie(cookie);

            Map<String, Claim> claims = Objects.requireNonNull(JWTHelper.ParseJWT(id_token)).getClaims();

            if(userService.findUserByUserADid(claims.get("oid").asString()) == null)
                CreateUser(claims);

            return new ResponseEntity<>("Successfully logged in !",
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Client not logged in.",
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("id_token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntity<>("Successfully logged out !",
                HttpStatus.OK);
    }

    private void CreateUser(Map<String, Claim> claims)
    {
        User userToInsert = new User(
                claims.get("oid").asString(),
                claims.get("given_name").asString().substring(0, 1).toUpperCase() + claims.get("given_name").asString().substring(1),
                claims.get("family_name").asString().substring(0, 1).toUpperCase() + claims.get("family_name").asString().substring(1)
        );

        userService.saveOrUpdateUser(userToInsert);
    }
}