package restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import restapi.model.AuthError;
import restapi.model.CalculatedMetric;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class CalculatedMetricController {

    private final IJWTHelper jwthelper;

    public CalculatedMetricController(IJWTHelper jwthelper) {
        this.jwthelper = jwthelper;
    }

    @GetMapping(value = "/calculatedMetrics")
public ResponseEntity<?> calculatedMetrics(
            @CookieValue(value = "id_token") String id_token,
            @RequestParam(value="mac_address", defaultValue="0") String deviceMacAddress,
            @RequestParam(value="calculation_type", defaultValue="0") String calculation_type,
            @RequestParam(value="dateBegin", defaultValue="0") String dateBegin,
            @RequestParam(value="dateEnd", defaultValue="0") String dateEnd,
            @RequestParam(value="step", defaultValue="0") String step) {

        System.out.println("Asking for Calculated metrics");
        System.out.println("MAC ADDRESS : " + deviceMacAddress);
        System.out.println("CALCULATION TYPE : " + calculation_type);
        System.out.println("DATE BEGIN : " + dateBegin);
        System.out.println("DATE END : " + dateEnd);
        System.out.println("STEP : " + step);

        if(jwthelper.isUserAuthenticated(id_token))
        {
            String url = "http://25.27.177.101:57086/api/CalculatedMetric/";

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.ALL));

            HttpEntity<?> entity = new HttpEntity<>(headers);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("step", step)
                    .queryParam("calculationtype", calculation_type)
                    .queryParam("macaddress", deviceMacAddress)
                    .queryParam("dateDebut", dateBegin)
                    .queryParam("dateFin", dateEnd);

            System.out.println("FINAL URL : " + uriBuilder.toUriString());

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<CalculatedMetric>> response = null;
            try {
                response = restTemplate.exchange(
                        URLDecoder.decode(uriBuilder.toUriString(), "UTF-8"),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<CalculatedMetric>>(){});
            } catch (UnsupportedEncodingException e) {
                System.out.println("ERROR ON URLDECODER !");
            }

            System.out.println("RESULT : " + response.getBody());

            return new ResponseEntity<>(response.getBody(),
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new AuthError("Unauthorized access."),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}