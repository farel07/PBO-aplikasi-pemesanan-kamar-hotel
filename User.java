public abstract class User {
    protected string username;
    protected string password;

    public User(string username, string password) {
        this.username = username;
        this.password = password;
    }

    public void() {
        System.out.println(username + "Login berhasil");
    }

    public void() {
        System.out.println(username + "Log out berhasil");
    }
}