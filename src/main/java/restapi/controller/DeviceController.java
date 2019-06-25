package restapi.controller;

import org.springframework.web.bind.annotation.*;
import restapi.model.Device;
import restapi.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @GetMapping(value = "/device")
    public Device device(@RequestParam(value="deviceID", defaultValue="0") long deviceID) {
        return deviceService.findDeviceById(deviceID);
    }

}