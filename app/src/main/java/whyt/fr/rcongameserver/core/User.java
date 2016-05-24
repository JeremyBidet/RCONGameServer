package whyt.fr.rcongameserver.core;

/**
 * Created by Jeremy on 24/05/2016.
 */
public class User {

    private String username;
    private GrantType grant_type;

    public User(String username, GrantType grant_type) {
        this.username = username;
        this.grant_type = grant_type;
    }

    public String getUsername() {
        return this.username;
    }

    public GrantType getGrantType() {
        return this.grant_type;
    }

}
