package mx.uatx.uamcc.ado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import mx.uatx.uamcc.beans.DatosTO;

public class CapturaADO {
	
	public String guardarDato(DatosTO to){
		try{
			try {
				Conecta conecta = new Conecta();
				Connection connection = conecta.getConexion();
				
				String sql = "{ ? = call MERCADOUATX.PAUSUARIOS.FNGUARDARDATOS(?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, to.getNombre());
				callableStatement.setString(3, to.getNomUser());
				callableStatement.setString(4, to.getPassword());
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				if(num==1){
					return "SE GUARDO";
				}else{
					return "NO SE GUARDO";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("Erro en.."+ex.getMessage());
		}
		return "";
		
	}
	
	public List<DatosTO> obtenerId(String usu, String pwd) {
		List<DatosTO> listado = new ArrayList<DatosTO>();
		try {
			Conecta conecta = new Conecta();
			Connection connection = conecta.getConexion();
			String sql = "{ call MERCADOUATX.PAUSUARIOS.SPOBTENERID(?,?,?)} ";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, usu);
			callableStatement.setString(2, pwd);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet res = (ResultSet) callableStatement.getObject(3);
			while (res.next()) {
				DatosTO c = new DatosTO();
				System.out.println("Regresando...." + res.getInt(1));
				System.out.println("Regresando...." + res.getString(3));
				c.setId(res.getInt(1));
				c.setNombre(res.getString(2));
				c.setNomUser(res.getString(3));
				c.setPassword(res.getString(4));
				listado.add(c);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listado;
	}
	
	public String validarIngreso(String usu, String pass) {
		try {
			try {
				Conecta conecta = new Conecta();
				Connection connection = conecta.getConexion();
				String sql = "{ ? = call MERCADOUATX.PAUSUARIOS.FNLOGIN(?,?)} ";
				CallableStatement callableStatement = connection
						.prepareCall(sql);
				callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
				callableStatement.setString(2, usu);
				callableStatement.setString(3, pass);
				callableStatement.execute();
				if (callableStatement.getInt(1) == 1) {
					return callableStatement.getInt(1) + "@Bienvenido";
				} else {
					return callableStatement.getInt(1)
							+ "@El usuario no esta registrado en el sistema.";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}
		return "";
	}

	
}
