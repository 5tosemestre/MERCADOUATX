package mx.uatx.mercadouatx.controlador;



import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.uatx.mercadouatx.ado.CapturaPublicADO;
import mx.uatx.mercadouatx.ado.capturaPublicGralADO;
import mx.uatx.mercadouatx.beans.OfertaTO;
import mx.uatx.mercadouatx.beans.PublicacionTO;
import mx.uatx.mercadouatx.beans.publicacionesTO;



@ManagedBean(name="ListadoControladorPub")
@SessionScoped

public class ListadoCotroladorPub {

	private List<PublicacionTO> listar;
	private List<OfertaTO> listar2;
	private OfertaTO oferta;
	private OfertaTO seleccion;
	private publicacionesTO seleccion2;
	public ListadoCotroladorPub(){
		inicializar();
		
	}
	
	public void inicializar(){
		CapturaPublicADO captura = new CapturaPublicADO();
		seleccion2= new publicacionesTO();
		listar= captura.listarpublica();
		listar2=captura.listarOfertas();
		seleccion= new OfertaTO();
		oferta=new OfertaTO();
		
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
	public List<PublicacionTO> getListar() {
		return listar;
	}
public void actualizarOfe() {
		
		capturaPublicGralADO c = new capturaPublicGralADO();
		String resultado="";
		String resultado2="";
		oferta.getIdOf();
		seleccion.getIdpubli();
		 resultado = c.actualizarOferta(oferta);
		 resultado2= c.actualizarOferta(seleccion);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new javax.faces.application.FacesMessage(resultado));
		inicializar();
		
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

	
	public void setListar(List<PublicacionTO> listar) {
		this.listar = listar;
	}

	public List<OfertaTO> getListar2() {
		return listar2;
	}

	public void setListar2(List<OfertaTO> listar2) {
		this.listar2 = listar2;
	}


	public OfertaTO getOferta() {
		return oferta;
	}

	public void setOferta(OfertaTO oferta) {
		this.oferta = oferta;
	}

	public OfertaTO getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(OfertaTO seleccion) {
		this.seleccion = seleccion;
	}


	public publicacionesTO getSeleccion2() {
		return seleccion2;
	}

	public void setSeleccion2(publicacionesTO seleccion2) {
		this.seleccion2 = seleccion2;
	}


	

	
	
}
