package mx.uatx.mercadouatx.ado;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import mx.uatx.mercadouatx.beans.DatosTO;
import mx.uatx.mercadouatx.beans.OfertaTO;
import mx.uatx.mercadouatx.beans.PublicacionTO;
import mx.uatx.mercadouatx.beans.publicacionesTO;
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

			ms.setDesesta(res.getString(10));
			ms.setIdesta(res.getInt(9));
			ms.setDescat(res.getString(8));
			ms.setIdcat(res.getInt(7));
			ms.setIma3(res.getString(6));
			ms.setIma2(res.getString(5));
			ms.setIma(res.getString(4));
			ms.setDesCort(res.getString(3));
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
	
	
	
	public List<OfertaTO> listarOfertas(){
		 String idu = obtenerValorSesion("id");
		List<OfertaTO> listado = new ArrayList<OfertaTO>();
		try{
		try {
		Conexion conecta = new Conexion();
		Connection connection = conecta.getConexion();
		
		String sql = "{ call MERCADOUATX.PAMERCADOUATX.SPMOSTRAR_OFERTAS(?,?)} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.setString(1,idu);
		callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		callableStatement.execute();
		ResultSet res = (ResultSet) callableStatement.getObject(2);
	
		while(res.next()){
			OfertaTO ms=new OfertaTO();
			ms.setEstatus(res.getString(5));
			ms.setPtitulo(res.getString(4));
			ms.setUser(res.getString(3));
			ms.setIdpubli(res.getInt(2));
			ms.setIdOf(res.getInt(1));
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
	public String guardarOfer(publicacionesTO to){
		 String idu = obtenerValorSesion("id");
		try{
			try {
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
				
				String sql = "{ ? = call MERCADOUATX.PAMERCADOUATX.FNINSERTAR_OFERTA(?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, idu);
				callableStatement.setInt(3, to.getIdp());
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				if(num==1){
					return "Has realizado";
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
