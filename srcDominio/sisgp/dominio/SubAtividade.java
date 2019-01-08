package sisgp.dominio;

import java.util.Date;
import java.util.List;

public class SubAtividade extends EntidadeDominio{
	
	private String codigo;
	private String nome;
	private String descricao;
	private Date dtInicio;
	private Date dtFim;
	
	private Integer nrHorasPrevisto;
	private Atividade atividade;
	private StatusAtividade statusAtividade;
	private StatusSistemaAtividade stSistAtividade;
	private RecursoHumano recursoHumano;
	
	private List<ApontamentoHoras> listaApontamentoHoras;
	
	
	
	public SubAtividade() {}
	public SubAtividade(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public SubAtividade(Integer id) {
		this.id = id;
	}
	public SubAtividade(String nome) {
		this.nome = nome;
	}
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the dtInicio
	 */
	public Date getDtInicio() {
		return dtInicio;
	}
	/**
	 * @param dtInicio the dtInicio to set
	 */
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	/**
	 * @return the dtFim
	 */
	public Date getDtFim() {
		return dtFim;
	}
	/**
	 * @param dtFim the dtFim to set
	 */
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	/**
	 * @return the atividade
	 */
	public Atividade getAtividade() {
		return atividade;
	}
	/**
	 * @param atividade the atividade to set
	 */
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	/**
	 * @return the statusAtividade
	 */
	public StatusAtividade getStatusAtividade() {
		return statusAtividade;
	}
	/**
	 * @param statusAtividade the statusAtividade to set
	 */
	public void setStatusAtividade(StatusAtividade statusAtividade) {
		this.statusAtividade = statusAtividade;
	}
	/**
	 * @return the stSistAtividade
	 */
	public StatusSistemaAtividade getStSistAtividade() {
		return stSistAtividade;
	}
	/**
	 * @param stSistAtividade the stSistAtividade to set
	 */
	public void setStSistAtividade(StatusSistemaAtividade stSistAtividade) {
		this.stSistAtividade = stSistAtividade;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return the listaApontamentoHoras
	 */
	public List<ApontamentoHoras> getListaApontamentoHoras() {
		return listaApontamentoHoras;
	}
	/**
	 * @param listaApontamentoHoras the listaApontamentoHoras to set
	 */
	public void setListaApontamentoHoras(List<ApontamentoHoras> listaApontamentoHoras) {
		this.listaApontamentoHoras = listaApontamentoHoras;
	}
	/**
	 * @return the nrHorasPrevisto
	 */
	public Integer getNrHorasPrevisto() {
		return nrHorasPrevisto;
	}
	/**
	 * @param nrHorasPrevisto the nrHorasPrevisto to set
	 */
	public void setNrHorasPrevisto(Integer nrHorasPrevisto) {
		this.nrHorasPrevisto = nrHorasPrevisto;
	}
	

}
