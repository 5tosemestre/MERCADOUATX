package mx.uatx.mercadouatx.controlador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.uatx.mercadouatx.ado.CapturaComentario;
import mx.uatx.mercadouatx.beans.ComentariosTO;


@ManagedBean(name="ListadoControladorComen")
@SessionScoped
public class ListadoControladorComen {
	private ComentariosTO  comentario;
	private ComentariosTO comen;
	private List<ComentariosTO>	lista_comentarios;
	
	public ListadoControladorComen(){
		//CapturaComentario captura = new CapturaComentario();
		

			inicializar();

	}
	public void inicializar(){
		CapturaComentario captura = new CapturaComentario();	
		lista_comentarios = captura.buscarComentarios();
		comentario=new ComentariosTO();
		comen =new ComentariosTO();
				}
	
	public void guardar(){
		CapturaComentario captura = new CapturaComentario();
		String resultado=captura.guardarComentario(comentario);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
		
	}
	
	public void guardarLogue(){
		CapturaComentario captura = new CapturaComentario();
		String resultado=captura.guardarComentarioLogue(comen);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	 
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
	public ComentariosTO getComentario() {
		return comentario;
	}

	public void setComentario(ComentariosTO comentario) {
		this.comentario = comentario;
	}

	public List<ComentariosTO> getLista_comentarios() {
		return lista_comentarios;
	}

	public void setLista_comentarios(List<ComentariosTO> lista_comentarios) {
		this.lista_comentarios = lista_comentarios;
	}
	public ComentariosTO getComen() {
		return comen;
	}
	public void setComen(ComentariosTO comen) {
		this.comen = comen;
	}
	
	
	
	
}
