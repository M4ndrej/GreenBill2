package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import konfiguracija.Konfiguracija;

/**
 *
 * @author Andrej
 */
public class Konekcija {

    private static Konekcija instance;
    private Connection connection;
    private boolean connected = false;

    private Konekcija() {
        try {
            String url = Konfiguracija.getInstance().getPropertie("url");
            String username = Konfiguracija.getInstance().getPropertie("username");
            String password = Konfiguracija.getInstance().getPropertie("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            connected = true;
        } catch (SQLException ex) {
            connected = false;
        }
    }

    public static Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        try {
            return connected && connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void reconnect() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = Konfiguracija.getInstance().getPropertie("url");
                String username = Konfiguracija.getInstance().getPropertie("username");
                String password = Konfiguracija.getInstance().getPropertie("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                connected = true;
            }
        } catch (SQLException ex) {
            connected = false;
        }
    }

    public void zatvoriKonekciju() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konekcija sa bazom je zatvorena.");
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
