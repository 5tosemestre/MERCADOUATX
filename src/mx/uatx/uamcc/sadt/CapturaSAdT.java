package mx.uatx.uamcc.sadt;

import mx.uatx.uamcc.beans.DatosTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;




import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.jdbc.OracleTypes;

public class CapturaSAdT {
	public void redireccionar(String ruta) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(ruta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String guardarDato(DatosTO to){
		try{
			try {
				Conecta conecta = new Conecta();
				Connection connection = conecta.getConexion();
				
				String sql = "{ ? = call MERCADOUATX.PAUSUARIOS.FNGUARDARDATOS(?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, to.getNombre());
				callableStatement.setString(3, to.getNomusuario());
				callableStatement.setString(4, to.getPw());
							
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

	public String validarIngreso(Integer usu,String pass){
		
		try{
		try {
			Conecta conecta = new Conecta();
			Connection connection = conecta.getConexion();
			String sql = "{ ? = call MERCADOUATX.PAUSUARIOS.FNLOGIN(?,?)} ";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, Integer.valueOf(usu));
			callableStatement.setString(3, pass);
			callableStatement.execute();
			Integer num = callableStatement.getInt(1);
			
		
			if (num == 1) {
				
				redireccionar("menu.xhtml");
				
			}

			 else {

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("NO TIENES ACCESO"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} catch (Exception ex) {
		System.out.println("Erro en.." + ex.getMessage());
	}
		return"";
		}
	
	
	
	public List<DatosTO> listarusr(){
		List<DatosTO> listado = new ArrayList<DatosTO>();
		try{
		try {
		
		Conecta conecta = new Conecta();
		Connection connection = conecta.getConexion();
		
			String sql = "{? = call MERCADOUATX.PAUSUARIOS.FNLISTARUSUARIOS} ";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.execute();
		ResultSet res = (ResultSet) callableStatement.getObject(1);
		while(res.next()){
			DatosTO ms=new DatosTO();
		
		ms.setPw(res.getString(4));
		ms.setNomusuario(res.getString(3));
		
		ms.setNombre(res.getString(2));
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

	
	
		
}
