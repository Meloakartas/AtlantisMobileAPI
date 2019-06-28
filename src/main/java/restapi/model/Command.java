package restapi.model;

public class Command {

    private Long id;

    private String command;

    private String mac_address;

    public Command(Long id, String mac_address)
    {
        this.id = id;
        this.command = inputCommand(id);
        this.mac_address = mac_address;
    }

    private String inputCommand(Long id) {
        if (id == 1L) {
            return "This is command 1";
        } else if (id == 2L) {
            return "This is command 2";
        } else if (id == 3L) {
            return "This is command 3";
        } else if (id == 4L) {
            return "This is command 4";
        } else {
            return "Command does not exist";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }
}
