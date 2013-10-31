package mx.uatx.mercadouatx.controlador;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import mx.uatx.mercadouatx.ado.capturaPublicGralADO;
import mx.uatx.mercadouatx.beans.publicacionesTO;
@ManagedBean(name="ControladorDetalle")
@SessionScoped
public class ControladorDetalle {

private List<publicacionesTO> listarD;
private publicacionesTO ofert;
private List<publicacionesTO> resultado;

	public ControladorDetalle(){
		inicializar();
		
	}
	
	public void inicializar(){
		
		redireccionar("DetalleVenta.xhtml");
	}
public void obtenidp() {
		
	capturaPublicGralADO c = new capturaPublicGralADO();

		
	redireccionar("DetalleVenta.xhtml");
		System.out.println(ofert.getIdp());
		resultado=c.listadetalle(ofert);
		
		 
		//inicializar();
		
	}
public void redireccionar(String ruta){
	try{
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext context=FacesContext.getCurrentInstance();  
		context.getExternalContext().redirect(ruta);
	
	}catch(Exception ex){
		ex.printStackTrace();
	}
}

	public String obtenerValorSesion(String clave){
		System.out.println("usuario es: "+clave);
		try{
			System.out.println("usuario es: "+clave);
			FacesContext context=FacesContext.getCurrentInstance();
			String bd=(String)context.getExternalContext().getSessionMap().get(clave);
			return bd;
		}catch(Exception ex){
			return "";
		}
}

	public List<publicacionesTO> getListarD() {
		return listarD;
	}

	public void setListarD(List<publicacionesTO> listarD) {
		this.listarD = listarD;
	}

	public publicacionesTO getOfert() {
		return ofert;
	}

	public void setOfert(publicacionesTO ofert) {
		this.ofert = ofert;
	}

	public List<publicacionesTO> getResultado() {
		return resultado;
	}

	public void setResultado(List<publicacionesTO> resultado) {
		this.resultado = resultado;
	}

	
	
	
}