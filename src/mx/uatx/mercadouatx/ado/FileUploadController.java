package mx.uatx.mercadouatx.ado;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;


@ManagedBean(name = "fileuploadcontroller")
@RequestScoped
public class FileUploadController {
	private  UploadedFile image;

	String idu = obtenerValorSesion("id");
	public  void uploadAttachment() {

		Calendar Cal = Calendar.getInstance();

		//UploadedFile file = event.getFile();
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		String Titulo = ec.getRequestParameterMap().get("myform:titulo");
		String descCorta = ec.getRequestParameterMap().get("myform:DescCorta");
		String descLarga = ec.getRequestParameterMap().get("myform:DescLarga");
		String Precio = ec.getRequestParameterMap().get("myform:precio");
		
		String datetime = Cal.get(Calendar.YEAR) + "-"
				+ (Cal.get(Calendar.MONTH) + 1) + "-" + Cal.get(Calendar.DATE)
				+ "_" + Cal.get(Calendar.HOUR_OF_DAY) + "."
				+ Cal.get(Calendar.MINUTE) + "." + Cal.get(Calendar.SECOND)
				+ "." + Cal.get(Calendar.MILLISECOND);
		String filename = FilenameUtils.getName(image.getFileName());
		String basename = FilenameUtils.getBaseName(filename) +"_"+ datetime;
		String extension = "." + FilenameUtils.getExtension(filename);
		String filePath = ec.getRealPath(String.format("/resources/img/%s",
				basename + extension));
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(image.getContents());
			fos.flush();
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				String.format("Archivo cargado: %s ", image.getFileName()),
				String.format("Mensaje: %s", Titulo)));
		System.out.println(descCorta);
		System.out.println(descLarga);
		System.out.println(Precio);
	System.out.println(filePath);
        guardarDato(Titulo,descCorta,descLarga,Precio,filePath);
	}
	public String guardarDato(String titulo,String desCort,String descLarg,String precio,String imagen){
		try{
			try {
				Conexion conecta = new Conexion();
				Connection connection = conecta.getConexion();
				System.out.println(titulo);
				System.out.println(desCort);
				System.out.println(descLarg);
				System.out.println(imagen);
				System.out.println(precio);
				System.out.println(idu);
				String sql = "{ ? = call MERCADOUATX.PAMERCADOUATX.FNINSERTAR_VENTA(?,?,?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, titulo);
				callableStatement.setString(3, desCort);
				callableStatement.setString(4, descLarg);
				callableStatement.setString(5, imagen);
				callableStatement.setString(6,precio);
				callableStatement.setString(7,idu);
				
				
				
				
			
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				if(num==1){
					return "guardado";
				}else{
					return "Erro al guardar verifica tus datos";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("Erro en.."+ex.getMessage());
		}
		return "";
		
	}
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

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	

}
