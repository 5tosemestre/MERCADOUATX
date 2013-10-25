package mx.uatx.uamcc.controlador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.uatx.uamcc.ado.CapturaADO;
import mx.uatx.uamcc.beans.DatosTO;

@ManagedBean(name = "LoginControlador")
@SessionScoped
public class LoginControlador {

	private String nom;
	private String pwd;

	// **********************INICIO DE METODOS COMUNES ************** COPIARLOS
	// EN CADA CLASE

	/**
	 * Metodo que cierra la sesion actual
	 */
	public void cerrarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().invalidateSession();// Se borrar
																// valores en
																// sesion
			redireccionar("index.xhtml");
		} catch (Exception ex) {

		}
	}

	/**
	 * Metodo que indica si tiene un rol
	 * 
	 * @param rol
	 * @return
	 */
	public boolean tieneRol(String rol) {
		String valor = obtenerValorSesion(rol);
		if (valor == null)
			return false;
		if (!valor.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo para obtener un valor
	 * 
	 * @param clave
	 * @return
	 */
	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap()
					.get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * Metodo para subir un valor a sesion
	 * 
	 * @param clave
	 * @param valor
	 */
	public void subirValorSesion(String clave, String valor) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put(clave, valor);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Metodo que muestra un mensaje en pantalla
	 * 
	 * @param mensaje
	 */
	public void mostrarMensaje(String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(mensaje));
	}

	/**
	 * Metodo que redirecciona a la pagina indicada
	 * 
	 * @param ruta
	 */
	public void redireccionar(String ruta) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(ruta);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// **********************FIN DE METODOS COMUNES ************** COPIARLOS EN
	// CADA CLASE

	public void login() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().invalidateSession();
		} catch (Exception ex) {

		}
		CapturaADO c = new CapturaADO();
		String resultado = c.validarIngreso(nom, pwd);
		String lista[] = resultado.split("@");
		if (lista[0].equals("1")) {
			// SI ESTA LOGUEADO CARGAR LOS ROLES
			subirValorSesion("nombre", nom);// Se sube a sesion la clave
													// del usuario para
													// cualquier dato posterior
			
			System.out.println(obtenerValorSesion("nombre"));
			cargarIdUsuario(nom, pwd);
			
			mostrarMensaje(lista[1]);
			redireccionar("menu.xhtml");
			return;
		} else {

			mostrarMensaje(lista[1]);
			redireccionar("index.xhtml");
			return;
		}
	}
	
	public void cargarIdUsuario(String nom, String pwd) {
		CapturaADO c = new CapturaADO();
		List<DatosTO> listado = c.obtenerId(nom,pwd);
		
			subirValorSesion("id",listado.get(0).getId()+"");
			System.out.println(obtenerValorSesion("id"));
		
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}