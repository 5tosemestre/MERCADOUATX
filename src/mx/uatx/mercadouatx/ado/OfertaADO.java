package mx.uatx.mercadouatx.ado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import mx.uatx.mercadouatx.beans.OfertasTO;
import oracle.jdbc.OracleTypes;

public class OfertaADO {
	public List<OfertasTO> ListaOferta(){
		 String idu = obtenerValorSesion("id");
		List<OfertasTO> listado = new ArrayList<OfertasTO>();
		try{
		try {
		Conexion conecta = new Conexion();
		Connection connection = conecta.getConexion();
		
		String sql = "{? = call MERCADOUATX.PAMERCADOUATX.SPMOSTRAR_MISOFERTAS(?)} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.setString(2,idu);
		callableStatement.execute();
		ResultSet res = (ResultSet) callableStatement.getObject(1);
	
		while(res.next()){
			OfertasTO ms=new OfertasTO();

			ms.setDesesta(res.getString(3));
			ms.setIdesta(res.getInt(2));
			ms.setTitulo(res.getString(1));
		
		
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
