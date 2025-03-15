package Profiles;


import com.fasterxml.jackson.annotation.*;

import Profiles.*;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = User.class, name = "User"),
        @JsonSubTypes.Type(value = Admin.class, name = "Admin")
})
public abstract class Profile {

    private int ID;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;


    public Profile(int id){
        this.ID = id;
    }

    public Profile(int ID, String email, String username, String password, String firstName, String lastName) {
        this.ID = ID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public abstract String getType();
}
