package mx.uatx.uamcc.controlador;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.uatx.uamcc.sadt.CapturaSAdT;


import mx.uatx.uamcc.beans.DatosTO;

	@ManagedBean(name="Controlador")
	@SessionScoped
	
	public class Controlador {

		private List<DatosTO> listausuario;
		private DatosTO		dato;
		private DatosTO		seleccion;
				
		public Controlador(){
			inicializar();
		}
		
		public void inicializar(){
			CapturaSAdT capturaSAdT = new CapturaSAdT();
	
		  dato = 	new DatosTO();
		  seleccion = new DatosTO();
		  listausuario = capturaSAdT.listarusr();
					
	
		}

		public void guardar(){
			
			CapturaSAdT capturaADO = new CapturaSAdT();
			String resultado="";
			if(dato.getId()==null){
				resultado=capturaADO.guardarDato(dato);
					}
			else{
				//resultado=capturaADO.actualizarU(dato);
				
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(resultado));
			inicializar();
		}
		
		
		public List<DatosTO> getListausuario() {
			return listausuario;
		}

		public void setListausuario(List<DatosTO> listausuario) {
			this.listausuario = listausuario;
		}
		public DatosTO getDato() {
			return dato;
		}

		public void setDato(DatosTO dato) {
			this.dato = dato;
		}

		public DatosTO getSeleccion() {
			return seleccion;
		}

		public void setSeleccion(DatosTO seleccion) {
			this.seleccion = seleccion;
		}

		
	}	