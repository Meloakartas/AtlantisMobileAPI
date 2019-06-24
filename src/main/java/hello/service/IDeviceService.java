package hello.service;

import hello.model.Device;
import java.util.List;

public interface IDeviceService {
    List<Device> findAll();
    Device findDeviceById(long id);
}