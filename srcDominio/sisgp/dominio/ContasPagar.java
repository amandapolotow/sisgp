package sisgp.dominio;

import java.util.Date;

public class ContasPagar extends EntidadeDominio {
	
	private Date dtLancamento;
	private Date dtVencimento;
	private Double valor;
	private Integer nrParcelas;
	private String contrato;
	private String observacao;
	//private Integer idProjeto;
	
	private CategoriaContasPagar categoriaContasPagar;
	private StatusContas statusContas;
	private Projeto projeto;
	
	public ContasPagar() {}
	public ContasPagar(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dtLancamento
	 */
	public Date getDtLancamento() {
		return dtLancamento;
	}
	/**
	 * @param dtLancamento the dtLancamento to set
	 */
	public void setDtLancamento(Date dtLancamento) {
		this.dtLancamento = dtLancamento;
	}
	/**
	 * @return the dtVencimento
	 */
	public Date getDtVencimento() {
		return dtVencimento;
	}
	/**
	 * @param dtVencimento the dtVencimento to set
	 */
	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
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
	/**
	 * @return the nrParcelas
	 */
	public Integer getNrParcelas() {
		return nrParcelas;
	}
	/**
	 * @param nrParcelas the nrParcelas to set
	 */
	public void setNrParcelas(Integer nrParcelas) {
		this.nrParcelas = nrParcelas;
	}
	/**
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}
	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	/**
	 * @return the categoriaContasPagar
	 */
	public CategoriaContasPagar getCategoriaContasPagar() {
		return categoriaContasPagar;
	}
	/**
	 * @param categoriaContasPagar the categoriaContasPagar to set
	 */
	public void setCategoriaContasPagar(CategoriaContasPagar categoriaContasPagar) {
		this.categoriaContasPagar = categoriaContasPagar;
	}
	/**
	 * @return the statusContas
	 */
	public StatusContas getStatusContas() {
		return statusContas;
	}
	/**
	 * @param statusContas the statusContas to set
	 */
	public void setStatusContas(StatusContas statusContas) {
		this.statusContas = statusContas;
	}
	/**
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}
	/**
	 * @param projeto the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


}
