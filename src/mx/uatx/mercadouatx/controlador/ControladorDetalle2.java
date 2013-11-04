package mx.uatx.mercadouatx.controlador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



import mx.uatx.mercadouatx.ado.capturaPublicGralADO;
import mx.uatx.mercadouatx.beans.ComentariosTO;
import mx.uatx.mercadouatx.beans.publicacionesTO;
@ManagedBean(name="ControladorDetalle2")
@SessionScoped
public class ControladorDetalle2 {

private List<publicacionesTO> listarD;
private publicacionesTO ofert;
private List<publicacionesTO> resultado;
private List<ComentariosTO> idpubli;

private List<publicacionesTO>	lista_comentarios;
private List<publicacionesTO>	resultado2;
private publicacionesTO  comentario;
	public ControladorDetalle2(){
		inicializar();
		
	}
	
	public void inicializar(){
		capturaPublicGralADO captura = new 	capturaPublicGralADO();
		lista_comentarios = captura.buscarComentarios();
		
		redireccionar("DetalleVenta2.xhtml");
	}
public void obtenidp() {
		
	capturaPublicGralADO c = new capturaPublicGralADO();
	

		
	redireccionar("DetalleVenta2.xhtml");
	

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
	
	
	/////////////comentarios
	public void guardar(){
		capturaPublicGralADO captura = new capturaPublicGralADO();
		
		System.out.println("este es el id"+ ofert.getIdp());
		String resultado=captura.guardarComentario(ofert);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
		
	}	
	public void guardarLogue(){
		capturaPublicGralADO captura = new capturaPublicGralADO();
		String resultado=captura.guardarComentarioLogue(ofert);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	 
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

	public List<ComentariosTO> getIdpubli() {
		return idpubli;
	}

	public void setIdpubli(List<ComentariosTO> idpubli) {
		this.idpubli = idpubli;
	}

	public List<publicacionesTO> getLista_comentarios() {
		return lista_comentarios;
	}

	public void setLista_comentarios(List<publicacionesTO> lista_comentarios) {
		this.lista_comentarios = lista_comentarios;
	}

	public List<publicacionesTO> getResultado2() {
		return resultado2;
	}

	public void setResultado2(List<publicacionesTO> resultado2) {
		this.resultado2 = resultado2;
	}

	public publicacionesTO getComentario() {
		return comentario;
	}

	public void setComentario(publicacionesTO comentario) {
		this.comentario = comentario;
	}

	
	
	
}