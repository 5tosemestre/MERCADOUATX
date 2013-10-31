package mx.uatx.mercadouatx.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.uatx.mercadouatx.ado.CapturaPublicADO;
import mx.uatx.mercadouatx.beans.publicacionesTO;


@ManagedBean(name="ListadoControladorNew")
@SessionScoped



public class ListadoControladorNew {
	
	private publicacionesTO newofer;
	public void inicializar(){
		newofer= new publicacionesTO();
		
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
public void guardar() {
		
		CapturaPublicADO c = new CapturaPublicADO();
		String resultado="";
		newofer.getIdp();
		 resultado = c.guardarOfer(newofer);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new javax.faces.application.FacesMessage(resultado));
		inicializar();
		
	}

	public publicacionesTO getNewofer() {
		return newofer;
	}
	public void setNewofer(publicacionesTO newofer) {
		this.newofer = newofer;
	}
}
