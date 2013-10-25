
package mx.uatx.uamcc.ado;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conecta {
	private static Connection connection=null;
	public Connection getConexion(){
		if(connection==null){
			try {
				System.out.println("Obteniendo conexion...1");
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				connection = DriverManager.getConnection("jdbc:oracle:thin:MERCADOUATX/MERCADOUATX@localhost:1521:XE");
				System.out.println("Obteniendo conexion...");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		return connection;
	}

}