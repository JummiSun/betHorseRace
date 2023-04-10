

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private final Map<String, Player> users;
    private String currentUsername = null;
    private final Player systemAdmin = new Player("admin", "admin", true);
    private boolean adminLoggedIn = false;

    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

    public PlayerManager() {
        this.users = new HashMap<>();
    }

    public void register(String login, String password, boolean admin) {
        if (login.equals(systemAdmin.getLogin())) {
            throw new IllegalArgumentException("You can't register player with admin");
        }
        if (users.containsKey(login)) {
            throw new IllegalArgumentException("Player with this name already exists");
        }
        Player user = new Player(login, password, admin);
        users.put(login, user);
    }

    public void login(String username, String password) {
        Player foundUser = users.get(username);
        if (foundUser == null) {
            throw new IllegalArgumentException("Player with this name does not exist");
        }
        if (!foundUser.getPassword().equals(password)) {
            throw new IllegalArgumentException("Player with this password does not match");
        }
        currentUsername = username;
    }

    public void logout() {
        currentUsername = null;
    }

    public String getCurrentPlayer() {
        return currentUsername;
    }

    public boolean isPlayerLoggedIn() {
        return currentUsername != null;
    }

    public void adminLogin(String login, String password) {
        if (!systemAdmin.getLogin().equals(login) || !systemAdmin.getPassword().equals(password)) {
            throw new IllegalArgumentException("You entered wrong admin name or password");
        }
        adminLoggedIn = true;
    }
}
