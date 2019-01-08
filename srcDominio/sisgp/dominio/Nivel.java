package sisgp.dominio;

public class Nivel extends EntidadeDominio{
	
	String nivel;
	
	public Nivel() {}
	public Nivel(Integer id,String nivel) {
		
		this.id = id;
		this.nivel = nivel;
		
	}
	
	public Nivel(Integer id) {
		this.id = id;
	}
	
	public Nivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
