package mx.uatx.mercadouatx.ado;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


import mx.uatx.mercadouatx.beans.PublicacionTO;
import oracle.jdbc.OracleTypes;

public class CapturaPublicADO {
	
	
				
	public List<PublicacionTO> listarpublica(){
		 String idu = obtenerValorSesion("id");
		List<PublicacionTO> listado = new ArrayList<PublicacionTO>();
		try{
		try {
		Conexion conecta = new Conexion();
		Connection connection = conecta.getConexion();
		
		String sql = "{? = call MERCADOUATX.PAMERCADOUATX.FNMOSTRAR_PUBLICACIONES(?)} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.setString(2,idu);
		callableStatement.execute();
		ResultSet res = (ResultSet) callableStatement.getObject(1);
	
		while(res.next()){
			PublicacionTO ms=new PublicacionTO();

			ms.setDesesta(res.getString(7));
			ms.setIdesta(res.getInt(6));
			ms.setDescat(res.getString(5));
			ms.setIdcat(res.getInt(4));
			ms.setIma(res.getString(3));
			ms.setTitulo(res.getString(2));
			ms.setIdp(res.getInt(1));
		
		
		listado.add(ms);
		}

	} catch (Exception e) {
		e.printStackTrace();
		}
		}catch(Exception ex){
		System.out.println("Erro en.."+ex.getMessage());
		}
		return listado;
		}
	
	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap()
					.get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}
}
