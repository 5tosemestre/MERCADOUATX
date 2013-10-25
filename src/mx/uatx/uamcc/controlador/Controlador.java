package mx.uatx.uamcc.controlador;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.uatx.uamcc.ado.CapturaADO;
import mx.uatx.uamcc.beans.DatosTO;

	@ManagedBean(name="Controlador")
	@SessionScoped
	
	public class Controlador {
		private DatosTO usuario;
		
		
		public Controlador(){
			inicializar();
		}
		
		public void inicializar(){
			usuario = new DatosTO();
	
		}

		public void guardar(){
			
			CapturaADO capturaADO = new CapturaADO();
			String resultado="";
			resultado=capturaADO.guardarDato(usuario);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(resultado));
			inicializar();
		}

		public DatosTO getUsuario() {
			return usuario;
		}

		public void setUsuario(DatosTO usuario) {
			this.usuario = usuario;
		}
	
		
	}	