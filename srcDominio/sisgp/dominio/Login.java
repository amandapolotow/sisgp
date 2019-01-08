package sisgp.dominio;

public class Login extends EntidadeDominio {
	
	private String nome;
	private String email;
	private String senha;
	private Boolean flgNaoEncontrado = false;
	
	private GrupoAcesso grupoAcesso;
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return the grupoAcesso
	 */
	public GrupoAcesso getGrupoAcesso() {
		return grupoAcesso;
	}
	/**
	 * @param grupoAcesso the grupoAcesso to set
	 */
	public void setGrupoAcesso(GrupoAcesso grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}
	/**
	 * @return the flNaoEncontrado
	 */
	public Boolean getFlgNaoEncontrado() {
		return flgNaoEncontrado;
	}
	/**
	 * @param flNaoEncontrado the flNaoEncontrado to set
	 */
	public void setFlgNaoEncontrado(Boolean flNaoEncontrado) {
		this.flgNaoEncontrado = flNaoEncontrado;
	}
	
	

}
