package sisgp.dominio;

public class CategoriaContasPagar extends EntidadeDominio {
	
	private String categoria;
	private Double valor;
	
	public CategoriaContasPagar() {}
	public CategoriaContasPagar(Integer id, String categoria, Double valor) {
		this.id = id;
		this.categoria = categoria;
		this.valor = valor;
	}
	public CategoriaContasPagar(Integer id, String categoria) {
		this.id = id;
		this.categoria = categoria;
	}
	public CategoriaContasPagar(Integer id) {
		this.id = id;
	}
	public CategoriaContasPagar(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

}
