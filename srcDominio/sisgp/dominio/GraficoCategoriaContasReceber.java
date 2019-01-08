package sisgp.dominio;

import java.util.Date;

public class GraficoCategoriaContasReceber extends EntidadeDominio{
	
	private Double vlTotal;
	private String categoria;
	private Date dtDe;
	private Date dtAte;
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
