package view;

//info class for users
public class Userinfo {

    public String email;
    public String password;

    public Userinfo(String e, String p){
        email = e;
        password = p;
    }

    public String getemail(){return email;}
    public String getPassword(){return password;}
}
