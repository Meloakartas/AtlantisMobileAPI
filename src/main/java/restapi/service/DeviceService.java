package restapi.service;

import restapi.model.Device;
import restapi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DeviceService implements IDeviceService {

    @Autowired
    private DeviceRepository repository;

    @Override
    public List<Device> findAll() {
        return (List<Device>) repository.findAll();
    }

    @Override
    public Device findDeviceById(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}