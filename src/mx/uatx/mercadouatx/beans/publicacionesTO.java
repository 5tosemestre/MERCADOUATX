package mx.uatx.mercadouatx.beans;

public class publicacionesTO {
	public String getTtulo() {
		return ttulo;
	}
	public void setTtulo(String ttulo) {
		this.ttulo = ttulo;
	}
	private Integer idp;
	private String ttulo;
	private String imagen;
	private Integer idcat;
	private String descat;
	private Integer idesta;
	private String desCorta;
	private String desEsta;
	private String desLar;
	private String tt;
	private Integer precio;
	public Integer getIdp() {
		return idp;
	}
	
	public String getTt() {
		return tt;
	}
	public void setTt(String tt) {
		this.tt = tt;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Integer getIdcat() {
		return idcat;
	}
	public void setIdcat(Integer idcat) {
		this.idcat = idcat;
	}
	public String getDescat() {
		return descat;
	}
	public void setDescat(String descat) {
		this.descat = descat;
	}
	public Integer getIdesta() {
		return idesta;
	}
	public void setIdesta(Integer idesta) {
		this.idesta = idesta;
	}
	
	public void setIdp(Integer idp) {
		this.idp = idp;
	
	}
	public String getDesCorta() {
		return desCorta;
	}
	public void setDesCorta(String desCorta) {
		this.desCorta = desCorta;
	}
	public String getDesLar() {
		return desLar;
	}
	public void setDesLar(String desLar) {
		this.desLar = desLar;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public String getDesEsta() {
		return desEsta;
	}
	public void setDesEsta(String desEsta) {
		this.desEsta = desEsta;
	}

}
