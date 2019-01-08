package sisgp.dominio;

import java.util.Date;
import java.util.List;

public class ControleFinanceiroProjeto extends EntidadeDominio {
	
	private Projeto projeto;
	private List<ContasPagar> listaContasPagar;
	private List<ContasReceber> listaContasReceber;
	private Date dtDe;
	private Date dtAte;
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
	 * @return the listaContasPagar
	 */
	public List<ContasPagar> getListaContasPagar() {
		return listaContasPagar;
	}
	/**
	 * @param listaContasPagar the listaContasPagar to set
	 */
	public void setListaContasPagar(List<ContasPagar> listaContasPagar) {
		this.listaContasPagar = listaContasPagar;
	}
	/**
	 * @return the listaContasReceber
	 */
	public List<ContasReceber> getListaContasReceber() {
		return listaContasReceber;
	}
	/**
	 * @param listaContasReceber the listaContasReceber to set
	 */
	public void setListaContasReceber(List<ContasReceber> listaContasReceber) {
		this.listaContasReceber = listaContasReceber;
	}
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

}
