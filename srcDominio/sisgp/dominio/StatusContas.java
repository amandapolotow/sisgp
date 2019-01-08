package sisgp.dominio;

public class StatusContas extends EntidadeDominio {
	String status;
	
	public StatusContas() {}
	public StatusContas(Integer id, String status) {
		this.id = id;
		this.status = status;
	}
	public StatusContas(Integer id) {
		this.id = id;
	}
	public StatusContas(String status) {
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
