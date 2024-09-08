package no.hvl.experiment1.domain;

public class User {
    private long id;
    private String username;
    private String email;


    //Constructor
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    //get / set
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = (int) id;
    }
    public long getId() {
        return id;
    }

}
