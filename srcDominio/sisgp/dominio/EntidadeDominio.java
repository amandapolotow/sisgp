package sisgp.dominio;

public class EntidadeDominio implements InterfaceEntidade {
	
	protected Integer id = 0;
	private boolean stAtivo;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the stAtivo
	 */
	public boolean isStAtivo() {
		return stAtivo;
	}
	/**
	 * @param stAtivo the stAtivo to set
	 */
	public void setStAtivo(boolean stAtivo) {
		this.stAtivo = stAtivo;
	}

}
