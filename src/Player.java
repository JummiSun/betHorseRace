
import java.util.Objects;

public class Player {
    private final String login;
    private final String password;
    private final boolean admin;

    public Player(String login, String password, boolean admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(login, player.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}

