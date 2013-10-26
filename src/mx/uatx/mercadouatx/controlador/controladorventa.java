package mx.uatx.mercadouatx.controlador;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import mx.uatx.mercadouatx.ado.VentaADO;

import mx.uatx.mercadouatx.beans.ventaTO;




	@ManagedBean(name="Controladorventa")
	@SessionScoped
	
	public class controladorventa {
		private ventaTO oferta;
		
		
		public controladorventa(){
			inicializar();
		}
		
		public void inicializar(){
			oferta = new ventaTO();
	
		}

		public void guardarventa(){
			
			VentaADO ventaADO = new VentaADO();
			String resultado="";
			resultado= ventaADO.guardarDato(oferta);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(resultado));
			inicializar();
		}

		public ventaTO getOferta() {
			return oferta;
		}

		public void setOferta(ventaTO oferta) {
			this.oferta = oferta;
		}

	
		
	}	