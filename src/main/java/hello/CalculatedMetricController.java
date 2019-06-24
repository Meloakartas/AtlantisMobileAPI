package hello;

import hello.model.CalculatedMetric;
import hello.model.Device;
import hello.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@RestController
public class CalculatedMetricController {

        @RequestMapping("/calculatedMetrics")
    public List<CalculatedMetric> calculatedMetrics(
            @RequestParam(value="deviceID", defaultValue="0") long deviceID,
            @RequestParam(value="metricTypeID", defaultValue="0") long metricTypeID,
            @RequestParam(value="dateBegin", defaultValue="0") String dateBegin,
            @RequestParam(value="dateEnd", defaultValue="0") String dateEnd) {

        //return new User(); //TODO: Call C# API to get calculated metrics
            throw new NotImplementedException();

    }

}