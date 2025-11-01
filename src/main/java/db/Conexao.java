package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class Conexao {
    private static final String PROPS_FILE = "/config.properties";

    public static Connection conectar() {
        try (InputStream input = Conexao.class.getResourceAsStream(PROPS_FILE)) {
            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexão bem-sucedida!");
            return conn;
        } catch (SQLException | IOException e) {
            System.out.println("❌ Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}

