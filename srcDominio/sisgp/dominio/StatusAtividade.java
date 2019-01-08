package sisgp.dominio;

public class StatusAtividade extends EntidadeDominio {
	
	private String status;
	
	public StatusAtividade() {}
	public StatusAtividade(Integer id, String status) {
		this.id = id;
		this.setStatus(status);
	}
	public StatusAtividade(Integer id) {
		this.id = id;
	}
	public StatusAtividade(String status) {
		this.setStatus(status);
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
