package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.model.AuthError;
import restapi.service.IDeviceService;

@RestController
public class DeviceController {

    private final IDeviceService deviceService;

    private final IJWTHelper jwthelper;

    public DeviceController(IDeviceService deviceService, IJWTHelper jwthelper) {
        this.deviceService = deviceService;
        this.jwthelper = jwthelper;
    }

    @GetMapping(value = "/device")
    public  ResponseEntity<?> device(@CookieValue(value = "id_token") String id_token, @RequestParam(value="deviceID", defaultValue="0") long deviceID) {
        System.out.println("Getting device " + deviceID);
        if(jwthelper.isUserAuthenticated(id_token))
        {
            return new ResponseEntity<>(deviceService.findDeviceById(deviceID),
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new AuthError("Unauthorized access."),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}