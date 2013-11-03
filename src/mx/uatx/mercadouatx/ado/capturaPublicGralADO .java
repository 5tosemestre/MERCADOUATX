package mx.uatx.mercadouatx.ado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Calendar;
import java.util.List;

import oracle.jdbc.OracleTypes;


//import mx.uatx.mercadouatx.beans.ComentariosTO;
import mx.uatx.mercadouatx.beans.OfertaTO;
import mx.uatx.mercadouatx.beans.categoriaTO;
import mx.uatx.mercadouatx.ado.Conexion;
import mx.uatx.mercadouatx.beans.publicacionesTO;





public class capturaPublicGralADO {


	
	
	
	public List<publicacionesTO> listadetalle(publicacionesTO to) {
		List<publicacionesTO> listado = new ArrayList<publicacionesTO>();
		//System.out.println("id publi"+ to.getIdp());
		try{
		try {	
		Conexion conecta= new Conexion();
		Connection connection = conecta.getConexion();
		String sql = "{call MERCADOUATX.PAMERCADOUATX.SPDETALLE_VENTA(?,?)} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.setInt(1, to.getIdp());
		callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		callableStatement.execute();	
		ResultSet res = (ResultSet) callableStatement.getObject(2);
		while(res.next()){
			publicacionesTO ms=new publicacionesTO();	
			ms.setDesEsta(res.getString(6));
			ms.setImagen(res.getString(5));
			ms.setPrecio(res.getInt(4));
			ms.setDesLar(res.getString(3));
			ms.setTtulo(res.getString(2));
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

		
	public List<publicacionesTO> listarpublica(){
		List<publicacionesTO> listado = new ArrayList<publicacionesTO>();
		try{
		try {	
		Conexion conecta= new Conexion();
		Connection connection = conecta.getConexion();
		String sql = "{? = call MERCADOUATX.PAMERCADOUATX.MOSTRAR_PUBLICA()} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.execute();
		ResultSet res = (ResultSet) callableStatement.getObject(1);
		while(res.next()){
			publicacionesTO ms=new publicacionesTO();
            
			ms.setDesCorta(res.getString(8));
			ms.setDesEsta(res.getString(7));
			ms.setIdesta(res.getInt(6));
			ms.setDescat(res.getString(5));
			ms.setIdcat(res.getInt(4));
			ms.setImagen(res.getString(3));
			ms.setTtulo(res.getString(2));
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

	public List<categoriaTO> buscarcategorias() {
		List<categoriaTO> listado = new ArrayList<categoriaTO>();
		try{
		try {	
		Conexion conecta= new Conexion();
		Connection connection = conecta.getConexion();
		String sql = "{call MERCADOUATX.PAMERCADOUATX.MOSTRAR_CATE(?)} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.execute();
		ResultSet res = (ResultSet) callableStatement.getObject(1);
		while(res.next()){
			categoriaTO ms=new categoriaTO();	
			ms.setDescripcion(res.getString(2));
			ms.setId(res.getInt(1));
			
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
	public List<publicacionesTO> listaxcategoria(categoriaTO to){
		List<publicacionesTO> listado = new ArrayList<publicacionesTO>();
		try{
		try {	
		Conexion conecta= new Conexion();
		Connection connection = conecta.getConexion();
		String sql = "{? = call MERCADOUATX.PAMERCADOUATX.BUSQUEDA(?)} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.setInt(2, to.getId());
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.execute();
		ResultSet res = (ResultSet) callableStatement.getObject(1);
		while(res.next()){
			publicacionesTO ms=new publicacionesTO();
            
			ms.setDesCorta(res.getString(8));
			ms.setDesEsta(res.getString(7));
			ms.setIdesta(res.getInt(6));
			ms.setDescat(res.getString(5));
			ms.setIdcat(res.getInt(4));
			ms.setImagen(res.getString(3));
			ms.setTtulo(res.getString(2));
			ms.setIdp(res.getInt(1));
			System.out.println(res.getString(8));
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
	public String actualizarOferta(OfertaTO  to){
		try{
			try {
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
				String sql = "{ ? = call MERCADOUATX.PAMERCADOUATX.FNACTUALIZAR_OFERTA(?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, to.getIdOf());
				callableStatement.setInt(3, to.getIdpubli());
				
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				
				if(num==1){
					String sql2 = "{ ? = call MERCADOUATX.PAMERCADOUATX.FNACTUALIZAR_PUBLI(?)} ";
					CallableStatement callableStatement1 = connection.prepareCall(sql2);
					callableStatement1.registerOutParameter(1, Types.INTEGER);
					callableStatement1.setInt(2, to.getIdpubli());
					
					callableStatement1.execute();
				}else{
					return " ";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("Erro en.."+ex.getMessage());
		}
		return "";
		
	}
	
	
	
	
	
	///comentarios
	
Calendar cal=Calendar.getInstance();
	
	

	public String fecha=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
	public String horas=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
	
	public String guardarComentario(publicacionesTO to){
		//Integer idpubli = ("idpubli");
		System.out.println("id publicacion DIANA "+ to.getIdp() );
		
		
	//	List<publicacionesTO> listado = new ArrayList<publicacionesTO>();
		try{
			try {
				
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
		
				String sql = "{ ? = call MERCADOUATX.PAMERCADOUATX.FNINSERTAR_COMENTARIO(?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
			String nick="ANÓNIMO";
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, to.getComen());
				callableStatement.setInt(3, to.getIdp());
				callableStatement.setString(4, nick );
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
	
	public List<publicacionesTO> buscarComentarios() {
		List<publicacionesTO> listado = new ArrayList<publicacionesTO>();
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
					publicacionesTO c=new publicacionesTO();
					c.setId(res.getInt(1));
					c.setComen(res.getString(2));
					c.setIdp(res.getInt(3));
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
	
}