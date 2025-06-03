package ba.unsa.etf.nbp.VehicleTrackPlatform.features.login;

public class LoginResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String token;

    public LoginResponse(String email, String firstName, String lastName, String token) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
