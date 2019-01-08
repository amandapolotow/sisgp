package sisgp.dominio;

import java.util.Date;
import java.util.List;

public class Projeto extends EntidadeDominio {
	
	private String codigo;
	private String nome;
	private String descricao;
	/*private Date dtInicio;
	private Date dtFim;
	private Date dtFimEfetiva;
	private Double orcamento;
	private Double vlFinalPrevisto;
	private Double vlFinalEfetivo;*/
	
	private RecursoHumano rhGestor;
	private StatusProjeto statusProjeto;
	private Cliente cliente;
	
	private List<Atividade> listaAtividade;
	private List<ContasPagar> listaContasPagar;
	private List<ContasReceber> listaContasReceber;

	
	public Projeto() {}
	public Projeto(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public Projeto(Integer id) {
		this.id = id;
	}
	public Projeto(String nome) {
		this.nome = nome;
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
	 * @return the rhGestor
	 */
	public RecursoHumano getRhGestor() {
		return rhGestor;
	}
	/**
	 * @param rhGestor the rhGestor to set
	 */
	public void setRhGestor(RecursoHumano rhGestor) {
		this.rhGestor = rhGestor;
	}
	/**
	 * @return the statusProjeto
	 */
	public StatusProjeto getStatusProjeto() {
		return statusProjeto;
	}
	/**
	 * @param statusProjeto the statusProjeto to set
	 */
	public void setStatusProjeto(StatusProjeto statusProjeto) {
		this.statusProjeto = statusProjeto;
	}
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the listaAtividade
	 */
	public List<Atividade> getListaAtividade() {
		return listaAtividade;
	}
	/**
	 * @param listaAtividade the listaAtividade to set
	 */
	public void setListaAtividade(List<Atividade> listaAtividade) {
		this.listaAtividade = listaAtividade;
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

	

}
