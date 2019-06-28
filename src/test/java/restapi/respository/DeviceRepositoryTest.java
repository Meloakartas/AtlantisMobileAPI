package restapi.respository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import restapi.model.Device;
import restapi.model.DeviceType;
import restapi.model.User;
import restapi.repository.DeviceRepository;
import restapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void testSaveDevice() {
        DeviceType deviceType = new DeviceType();
        Device deviceToCreate = new Device(1L, "macAddress", "name", deviceType);
        Device createdDevice = deviceRepository.save(deviceToCreate);
        Assert.assertNotNull(createdDevice);
    }

    @Test
    public void testFindDeviceById() {
        DeviceType deviceType = new DeviceType();
        Device deviceToCreate = new Device(1L, "macAddress", "name", deviceType);
        deviceRepository.save(deviceToCreate);
        Optional<Device> createdDevice = deviceRepository.findById(1L);
        Assert.assertEquals(deviceToCreate.getId(), createdDevice.get().getId());
    }

    @Test
    public void testFindAll() {
        DeviceType deviceType = new DeviceType();
        Device deviceToCreate = new Device(1L, "macAddress", "name", deviceType);
        deviceRepository.save(deviceToCreate);
        List<Device> devices = (List<Device>) deviceRepository.findAll();
        Assert.assertNotNull(devices);
    }
}