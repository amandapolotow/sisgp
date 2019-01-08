package sisgp.dominio;

import java.util.Date;

public class GraficoContasReceber extends EntidadeDominio{
	
	private Double vlTotal;
	private String mes;
	
	private Date dtDe;
	private Date dtAte;
	private Projeto projeto;

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}
	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
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
	 * @return the vlTotal
	 */
	public Double getVlTotal() {
		return vlTotal;
	}
	/**
	 * @param vlTotal the vlTotal to set
	 */
	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}

	


}
