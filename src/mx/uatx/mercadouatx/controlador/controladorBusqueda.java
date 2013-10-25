package mx.uatx.mercadouatx.controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.uatx.mercadouatx.ado.capturaPublicGralADO;
import mx.uatx.mercadouatx.beans.categoriaTO;
import mx.uatx.mercadouatx.beans.publicacionesTO;

@ManagedBean (name="controlador")
@SessionScoped

public class controladorBusqueda{
private List<publicacionesTO> listar;
private categoriaTO	categoria;
private List<categoriaTO> listacategorias;
	
	public controladorBusqueda (){
		inicializar();
	}
	public void inicializar(){
		
		capturaPublicGralADO datos1= new capturaPublicGralADO();
		listacategorias = datos1.buscarcategorias();
		categoria = new categoriaTO();
	    listar = datos1.listarpublica();
	    
	}
	public void buscarxcate(){
		
		capturaPublicGralADO datos1= new capturaPublicGralADO();
		listar=datos1.listaxcategoria(categoria);
		
		
	}
	
	public List<publicacionesTO> getListar() {
		return listar;
	}
	public void setListar(List<publicacionesTO> listar) {
		this.listar = listar;
	}
	public categoriaTO getCategoria() {
		return categoria;
	}
	public void setCategoria(categoriaTO categoria) {
		this.categoria = categoria;
	}
	public List<categoriaTO> getListacategorias() {
		return listacategorias;
	}
	public void setListacategorias(List<categoriaTO> listacategorias) {
		this.listacategorias = listacategorias;
	}
	
}
