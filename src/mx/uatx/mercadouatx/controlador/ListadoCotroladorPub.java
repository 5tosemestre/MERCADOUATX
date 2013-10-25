package mx.uatx.mercadouatx.controlador;



import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.uatx.mercadouatx.ado.CapturaPublicADO;
import mx.uatx.mercadouatx.beans.PublicacionTO;



@ManagedBean(name="ListadoControladorPub")
@SessionScoped

public class ListadoCotroladorPub {

	private List<PublicacionTO> listar;
	
	
	public ListadoCotroladorPub(){
		inicializar();
		
	}
	
	public void inicializar(){
		CapturaPublicADO captura = new CapturaPublicADO();
		
		listar= captura.listarpublica();
		
	
		
		
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
	public void setListar(List<PublicacionTO> listar) {
		this.listar = listar;
	}
	
	
}
