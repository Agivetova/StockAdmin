package kg.gulnaz;

public class Authorization {
    private String username;
    private String password;

    public Authorization() {
        setUsername("");
        setPassword("");
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

    public Boolean authorization(String username, String password){
        if(username.equals(getUsername()) && password.equals(getPassword())){
            return true;
        }
        return false;
    }
}
