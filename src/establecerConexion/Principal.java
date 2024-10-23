package establecerConexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		final String FILE_CONFIG = "dbconfig.properties";
		
		try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(FILE_CONFIG)));

            String nombreBaseDatos = properties.getProperty("PATH");
            String usuario = properties.getProperty("USER");
            String contrasenia = properties.getProperty("PASS");
    		
            File f = new File(nombreBaseDatos);
    		
    		if (f.exists()) {
    			String url = "jdbc:sqlite:" + nombreBaseDatos;
    			try (Connection c = DriverManager.getConnection(url, usuario, contrasenia);) {
    				if (c != null) {
    					System.out.println("Conexión establecida con éxito.");
    				} else {
    					System.out.println("Conexión no establecida.");
    				}
    			} catch (SQLException sqle) {
    				sqle.printStackTrace();
    			}
    		} else {
    			System.out.println("No se ha encontrado la base de datos.");
    		}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		sc.close();
	}
}
