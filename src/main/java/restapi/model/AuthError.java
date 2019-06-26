package restapi.model;

public class AuthError {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthError(String description)
    {
        this.description = description;
    }
}
