/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


/**
 *
 * @author Kary Johnson
 */
public class User{
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String userId;
    
    public User(){
        this.userId    = "";
        this.firstname = "";
        this.lastname  = "";
        this.email     = "";
        this.username  = "";
        this.password  = "";
    }
    public User(String firstname, String lastname, String email, String username,
            String password){
        this.firstname = firstname;
        this.lastname  = lastname;
        this.email     = email;
        this.username  = username;
        this.password  = password;
    }

    public User(String username, String password){
        this.username  = username;
        this.password  = password;
    }
    
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public String getUserId(){
        return userId;
    }
    
}
