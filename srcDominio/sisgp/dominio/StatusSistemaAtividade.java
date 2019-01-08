package sisgp.dominio;

public class StatusSistemaAtividade extends EntidadeDominio {
	
	private String status;
	
	public StatusSistemaAtividade() {}
	public StatusSistemaAtividade(Integer id, String status) {
		this.id = id;
		this.status = status;
	}
	public StatusSistemaAtividade(Integer id) {
		this.id = id;
	}
	public StatusSistemaAtividade(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
