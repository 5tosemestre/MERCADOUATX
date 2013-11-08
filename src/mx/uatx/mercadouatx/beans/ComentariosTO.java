package mx.uatx.mercadouatx.beans;

public class ComentariosTO {

	private Integer id;
	private String comen;
	private Integer idpubli;
	private String nombre;
	private String fecha;
	private String hora;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComen() {
		return comen;
	}
	public void setComen(String comen) {
		this.comen = comen;
	}
	public Integer getIdpubli() {
		return idpubli;
	}
	public void setIdpubli(Integer idpubli) {
		this.idpubli = idpubli;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
