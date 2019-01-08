package sisgp.dominio;

public class CategoriaContasReceber extends EntidadeDominio{

	private String categoria;
	private Double valor;
	
	public CategoriaContasReceber() {}
	public CategoriaContasReceber(Integer id, String categoria, Double valor) {
		this.id = id;
		this.categoria = categoria;
		this.valor = valor;
	}
	public CategoriaContasReceber(Integer id, String categoria) {
		this.id = id;
		this.categoria = categoria;
	}
	public CategoriaContasReceber(Integer id) {
		this.id = id;
	}
	public CategoriaContasReceber(String categoria) {
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
