package mx.uatx.mercadouatx.ado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import mx.uatx.mercadouatx.beans.categoriaTO;
import mx.uatx.mercadouatx.ado.Conexion;
import mx.uatx.mercadouatx.beans.publicacionesTO;





public class capturaPublicGralADO {
		
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
	
}