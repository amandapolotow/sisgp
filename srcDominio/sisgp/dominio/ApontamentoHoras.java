package sisgp.dominio;

import java.util.Date;

public class ApontamentoHoras extends EntidadeDominio{
		
	Integer nrHoras;
	Integer nrHorasExtras;
	Date data;
	Boolean stPago;
	
	SubAtividade SubAtividade;
	ContasPagar contasPagar;
	
	//para ser usado nas buscas por apontamentos
	Date dtDe;
	Date dtAte;
	
	public ApontamentoHoras() {}
	public ApontamentoHoras(Integer id) {
		this.id = id;
	}
	public ApontamentoHoras(Integer id, Integer nrHoras, Integer nrHorasExtras) {
		this.id = id;
		this.nrHoras = nrHoras;
		this.nrHorasExtras = nrHorasExtras;
	}
	
	/**
	 * @return the nrHoras
	 */
	public Integer getNrHoras() {
		return nrHoras;
	}
	/**
	 * @param nrHoras the nrHoras to set
	 */
	public void setNrHoras(Integer nrHoras) {
		this.nrHoras = nrHoras;
	}
	/**
	 * @return the nrHorasExtras
	 */
	public Integer getNrHorasExtras() {
		return nrHorasExtras;
	}
	/**
	 * @param nrHorasExtras the nrHorasExtras to set
	 */
	public void setNrHorasExtras(Integer nrHorasExtras) {
		this.nrHorasExtras = nrHorasExtras;
	}
	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * @return the stPago
	 */
	public Boolean getStPago() {
		return stPago;
	}
	/**
	 * @param stPago the stPago to set
	 */
	public void setStPago(Boolean stPago) {
		this.stPago = stPago;
	}
	/**
	 * @return the subAtividade
	 */
	public SubAtividade getSubAtividade() {
		return SubAtividade;
	}
	/**
	 * @param subAtividade the subAtividade to set
	 */
	public void setSubAtividade(SubAtividade subAtividade) {
		SubAtividade = subAtividade;
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
