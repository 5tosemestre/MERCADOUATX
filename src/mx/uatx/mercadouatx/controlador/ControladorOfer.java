package mx.uatx.mercadouatx.controlador;

import java.util.List;

import javax.faces.context.FacesContext;

import mx.uatx.mercadouatx.ado.OfertaADO;
import mx.uatx.mercadouatx.beans.OfertasTO;


public class ControladorOfer {

private List<OfertasTO> listar;
	
	
	public ControladorOfer(){
		inicializar();
		
	}
	
	public void inicializar(){
		OfertaADO captura = new OfertaADO();
		
		setListar(captura.ListaOferta());
					
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

	public List<OfertasTO> getListar() {
		return listar;
	}

	public void setListar(List<OfertasTO> listar) {
		this.listar = listar;
	}
}