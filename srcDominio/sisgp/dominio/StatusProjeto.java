package sisgp.dominio;

public class StatusProjeto extends EntidadeDominio {
	
	private String statusProjeto;
	
	public StatusProjeto() {}
	public StatusProjeto(Integer id) {
		this.id = id;
	}
	public StatusProjeto(String statusProjeto) {
		this.statusProjeto = statusProjeto;
	}
	public StatusProjeto(Integer id, String statusProjeto) {
		this.id = id;
		this.statusProjeto = statusProjeto;
	}

	/**
	 * @return the statusProjeto
	 */
	public String getStatusProjeto() {
		return statusProjeto;
	}

	/**
	 * @param statusProjeto the statusProjeto to set
	 */
	public void setStatusProjeto(String statusProjeto) {
		this.statusProjeto = statusProjeto;
	}

}
