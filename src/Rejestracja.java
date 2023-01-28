import java.sql.SQLException;

public class Rejestracja {
    public static void rejestracja(String user, String password) throws SQLException {
        SqlConn.koneksja("INSERT INTO users (login, password) values ('" + user + "','" + password + "')", true );
    }

}
