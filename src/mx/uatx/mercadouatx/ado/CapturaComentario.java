package mx.uatx.mercadouatx.ado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;

import oracle.jdbc.OracleTypes;

import mx.uatx.mercadouatx.beans.ComentariosTO;



public class CapturaComentario {
	Calendar cal=Calendar.getInstance();
	
	public String fecha=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
	public String horas=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
	public String guardarComentario(ComentariosTO to){
		
		try{
			try {
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
				String sql = "{ ? = call MERCADOUATX.PAMERCADOUATX.FNINSERTAR_COMENTARIO(?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, to.getComen());
				callableStatement.setInt(3, to.getIdpubli());
				callableStatement.setString(4, to.getNombre() );
				callableStatement.setString(5, horas);
			
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				if(num==1){
					return "Se guardo correctamente!";
				}else{
					return "No se pudo guardar!";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("Erro en.."+ex.getMessage());
		}
		return "";
		
	}
	public List<ComentariosTO> buscarComentarios() {
		List<ComentariosTO> listado = new ArrayList<ComentariosTO>();
		try{
			try {
				
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
				String sql = "{ call MERCADOUATX.PAMERCADOUATX.SPCOMENTARIOS(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
				callableStatement.execute();
				ResultSet res = (ResultSet) callableStatement.getObject(1);
				while(res.next()){
					ComentariosTO c=new ComentariosTO();
					c.setId(res.getInt(1));
					c.setComen(res.getString(2));
					c.setIdpubli(res.getInt(3));
					c.setNombre(res.getString(4));
					c.setFecha(res.getString(5));
					c.setHora(res.getString(6));

					listado.add(c);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("Erro en.."+ex.getMessage());
		}
		return listado;
	}
	
	public String guardarComentarioLogue(ComentariosTO to){
		 String nombre = obtenerValorSesion("nombre");
		 System.out.println(nombre);
		try{
			try {
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
				String sql = "{ ? = call MERCADOUATX.PAMERCADOUATX.FNINSERTAR_COMENTARIOLoge(?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, to.getComen());
				callableStatement.setInt(3, to.getIdpubli());
				callableStatement.setString(4,nombre);
				callableStatement.setString(5, horas);
			
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				if(num==1){
					return "Se guardo correctamente!";
				}else{
					return "No se pudo guardar!";
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
