package sisgp.dominio;

import java.util.Date;
import java.util.List;

public class ControleFinanceiro extends EntidadeDominio {
	
	private Date dtDe;
	private Date dtAte;
	private StatusContas statusContas;
	private Projeto projeto;
	
	private Double receita;
	private Double despesa;
	
	private List<CategoriaContasPagar> listaCategoriaContasPagar;
	private List<CategoriaContasReceber> listaCategoriaContasReceber;
	/**
	 * @return the dtDe
	 */
	public Date getDtDe() {
		return dtDe;
	}
	/**
	 * @param dtDe the dtDe to set
	 */
	public void setDtDe(Date dtDe) {
		this.dtDe = dtDe;
	}
	/**
	 * @return the dtAte
	 */
	public Date getDtAte() {
		return dtAte;
	}
	/**
	 * @param dtAte the dtAte to set
	 */
	public void setDtAte(Date dtAte) {
		this.dtAte = dtAte;
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
	/**
	 * @return the receita
	 */
	public Double getReceita() {
		return receita;
	}
	/**
	 * @param receita the receita to set
	 */
	public void setReceita(Double receita) {
		this.receita = receita;
	}
	/**
	 * @return the despesa
	 */
	public Double getDespesa() {
		return despesa;
	}
	/**
	 * @param despesa the despesa to set
	 */
	public void setDespesa(Double despesa) {
		this.despesa = despesa;
	}
	/**
	 * @return the listaCategoriaContasPagar
	 */
	public List<CategoriaContasPagar> getListaCategoriaContasPagar() {
		return listaCategoriaContasPagar;
	}
	/**
	 * @param listaCategoriaContasPagar the listaCategoriaContasPagar to set
	 */
	public void setListaCategoriaContasPagar(List<CategoriaContasPagar> listaCategoriaContasPagar) {
		this.listaCategoriaContasPagar = listaCategoriaContasPagar;
	}
	/**
	 * @return the listaCategoriaContasReceber
	 */
	public List<CategoriaContasReceber> getListaCategoriaContasReceber() {
		return listaCategoriaContasReceber;
	}
	/**
	 * @param listaCategoriaContasReceber the listaCategoriaContasReceber to set
	 */
	public void setListaCategoriaContasReceber(List<CategoriaContasReceber> listaCategoriaContasReceber) {
		this.listaCategoriaContasReceber = listaCategoriaContasReceber;
	}
	
	


}
