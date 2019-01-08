package sisgp.dominio;

import java.util.Date;

public class GerarContas extends EntidadeDominio{
	
	private Date dtDe;
	private Date dtAte;
	private ContasPagar contasPagar;
	private RecursoHumano recursoHumano;
	private Projeto projeto;
	
	

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
	 * @return the contasPagar
	 */
	public ContasPagar getContasPagar() {
		return contasPagar;
	}

	/**
	 * @param contasPagar the contasPagar to set
	 */
	public void setContasPagar(ContasPagar contasPagar) {
		this.contasPagar = contasPagar;
	}

	/**
	 * @return the recursoHumano
	 */
	public RecursoHumano getRecursoHumano() {
		return recursoHumano;
	}

	/**
	 * @param recursoHumano the recursoHumano to set
	 */
	public void setRecursoHumano(RecursoHumano recursoHumano) {
		this.recursoHumano = recursoHumano;
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
