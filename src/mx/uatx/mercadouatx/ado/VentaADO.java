package mx.uatx.mercadouatx.ado;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Types;

import javax.faces.context.FacesContext;

import mx.uatx.mercadouatx.beans.ventaTO;




public class VentaADO {
	 String idu = obtenerValorSesion("id");
	public String guardarDato(ventaTO to){
		try{
			try {
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
				
				String sql = "{ ? = call MERCADOUATX.MERCADOUATX.FNINSERTAR_VENTA(?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, to.getTitulo());
				callableStatement.setString(3, to.getDescripcionCort());
				callableStatement.setString(4, to.getDescripcionLarg());
				callableStatement.setString(5, idu);
			
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				if(num==1){
					return "guardado";
				}else{
					return "Erro al guardar verifica tus datos";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("Erro en.."+ex.getMessage());
		}
		return "";
		
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
