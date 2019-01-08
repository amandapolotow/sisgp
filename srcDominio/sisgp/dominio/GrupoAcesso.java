package sisgp.dominio;

public class GrupoAcesso extends EntidadeDominio {
	

	String grupoAcesso;
	
	public GrupoAcesso() {}
	public GrupoAcesso(Integer id, String grupoAcesso) {
		this.id = id;
		this.grupoAcesso = grupoAcesso;
	}
	public GrupoAcesso(Integer id) {
		this.id = id;
	}
	public GrupoAcesso(String grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}
	/**
	 * @return the grupoAcesso
	 */
	public String getGrupoAcesso() {
		return grupoAcesso;
	}
	/**
	 * @param grupoAcesso the grupoAcesso to set
	 */
	public void setGrupoAcesso(String grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}

}
