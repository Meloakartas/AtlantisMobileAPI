package restapi.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import restapi.model.AuthError;
import restapi.model.CalculatedMetric;
import restapi.model.Command;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class CommandController {

    private final IJWTHelper jwthelper;

    public CommandController(IJWTHelper jwthelper) {
        this.jwthelper = jwthelper;
    }

    @PostMapping(value = "/command")
    public ResponseEntity<?> sendCommand(
            @CookieValue(value = "id_token") String id_token,
            @RequestParam(value="id", defaultValue="0") long id,
            @RequestParam(value="mac_address", defaultValue="0") String mac_address) {

        System.out.println("Sending a command");
        if(jwthelper.isUserAuthenticated(id_token))
        {
            Command command = new Command(id, mac_address);
            StringEntity entity = new StringEntity(command.toString(),
                    ContentType.APPLICATION_JSON);

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("http://25.27.177.101:53144/ApiTest/api/Command/");
            request.setEntity(entity);

            try {
                HttpResponse response = httpClient.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(new AuthError("Command has been sent !"),
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new AuthError("Unauthorized access."),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}