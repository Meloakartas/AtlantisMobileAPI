package restapi.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import restapi.model.Device;
import restapi.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public Device device(@RequestParam(value="deviceID", defaultValue="0") long deviceID) {
        return deviceService.findDeviceById(deviceID);
    }

}