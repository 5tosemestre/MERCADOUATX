package mx.uatx.mercadouatx.beans;

public class publicacionesTO {
	public String getTtulo() {
		return ttulo;
	}
	public void setTtulo(String ttulo) {
		this.ttulo = ttulo;
	}
	private String ttulo;
	private String imagen;
	private Integer idcat;
	private String descat;
	private Integer idesta;
	private String desCorta;
	private String DesEsta;
	public Integer getIdp() {
		return idp;
	}
	private Integer idp;
	private String tt;
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
	public String getDesEsta() {
		return DesEsta;
	}
	public void setDesEsta(String desEsta) {
		DesEsta = desEsta;
	}

}
