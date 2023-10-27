package bg.sirma.ims.model.user;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private long id;
    private String username;
    private String password;
    private final Set<RoleEnum> roles;

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEnum> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void addRole(RoleEnum roleEnum) {
        this.roles.add(roleEnum);
    }
}
