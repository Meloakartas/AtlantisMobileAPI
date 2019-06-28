package restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import restapi.model.AuthError;
import restapi.model.CalculatedMetric;

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
            @RequestParam(value="deviceID", defaultValue="0") String deviceMacAddress,
            @RequestParam(value="metricTypeID", defaultValue="0") String dateStart,
            @RequestParam(value="dateBegin", defaultValue="0") String dateEnd,
            @RequestParam(value="dateEnd", defaultValue="0") int calculationType,
            @RequestParam(value="step", defaultValue="0") String step) {

        if(jwthelper.isUserAuthenticated(id_token))
        {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<CalculatedMetric>> response = restTemplate.exchange(
                    "http://25.27.177.101:57086/ApiTest/api/CalculatedMetrics?macaddress="+deviceMacAddress+"&dateDebut="+dateStart+"&dateFin="+dateEnd+"&calculationtype="+calculationType+"&step="+step,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CalculatedMetric>>(){});

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