
package mx.uatx.uamcc.controlador;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import mx.uatx.uamcc.sadt.CapturaSAdT;



	@ManagedBean(name="LoginControlador")
	@SessionScoped
	public class LoginControlador {
		private Integer id;
		private String nom;
		private String pwd;
		
				
		
		
		public void cerrarSesion(){
			try{
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().invalidateSession();//Se borrar valores en sesion
				redireccionar("logueo.xhtml");
			}catch(Exception ex){
				
			}
		}
		
		
		public String obtenerValorSesion(String clave){
			try{
				FacesContext context=FacesContext.getCurrentInstance();
				String bd=(String)context.getExternalContext().getSessionMap().get(clave);
				return bd;
			}catch(Exception ex){
				return "";
			}
		}
		
		

		public void subirValorSesion(String clave, String valor) {
			try {
				//System.out.println(clave);
				//System.out.println(valor);
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put(clave, valor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void redireccionar(String ruta) {
			try {
				System.out.println("REdc");
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect(ruta);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		

		public void ingresar() {
			System.out.println("ingresar");
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().invalidateSession();
			} catch (Exception ex) {

			}
			CapturaSAdT c = new CapturaSAdT();
			c.validarIngreso(id, pwd); 
			 
			//String lista[]=resultado.split("@");
			//if(lista[0].equals("1")){//SI ESTA LOGUEADO CARGAR LOS ROLES
				subirValorSesion("id",id+"");//Se sube a sesion la clave del usuario para cualquier dato posterior
				//cargarRolesUsuario(id);
				//mostrarMensaje(lista[1]);
				//redireccionar("principal.xhtml")
				return;
			//}
			//redireccionar("inicio.xhtml");

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

		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
	
	
	}