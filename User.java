public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void login() {
        System.out.println(username + " login berhasil");
    }

    public void logout() {
        System.out.println(username + " logout berhasil");
    }
}
