package sisgp.core.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sisgp.core.aplicacao.Resultado;
import sisgp.core.dao.ApontamentoHorasDao;
import sisgp.core.dao.AtividadeDao;
import sisgp.core.dao.CategoriaContasPagarDao;
import sisgp.core.dao.CategoriaContasReceberDao;
import sisgp.core.dao.ClienteDao;
import sisgp.core.dao.ContasPagarDao;
import sisgp.core.dao.ContasReceberDao;
import sisgp.core.dao.ControleEstoqueDao;
import sisgp.core.dao.ControleFinanceiroDao;
import sisgp.core.dao.GerarContasDao;
import sisgp.core.dao.GraficoCategoriaContasPagarDao;
import sisgp.core.dao.GraficoCategoriaContasReceberDao;
import sisgp.core.dao.GraficoContasPagarDao;
import sisgp.core.dao.GraficoContasReceberDao;
import sisgp.core.dao.GrupoAcessoDao;
import sisgp.core.dao.LoginDao;
import sisgp.core.dao.NivelDao;
import sisgp.core.dao.ProjetoDao;
import sisgp.core.dao.RecursoDao;
import sisgp.core.dao.RecursoHumanoDao;
import sisgp.core.dao.StatusAtividadeDao;
import sisgp.core.dao.StatusContasDao;
import sisgp.core.dao.StatusProjetoDao;
import sisgp.core.dao.StatusSistemaAtividadeDao;
import sisgp.core.dao.SubAtividadeDao;
import sisgp.core.interfaces.InterfaceDao;
import sisgp.core.interfaces.InterfaceFachada;
import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.core.negocio.CalcularGerarContas;
import sisgp.core.negocio.ValidadorApontamentoHoras;
import sisgp.core.negocio.ValidadorAtividade;
import sisgp.core.negocio.ValidadorCategoriaContasPagar;
import sisgp.core.negocio.ValidadorCategoriaContasReceber;
import sisgp.core.negocio.ValidadorCliente;
import sisgp.core.negocio.ValidadorContasPagar;
import sisgp.core.negocio.ValidadorContasReceber;
import sisgp.core.negocio.ValidadorControleEstoque;
import sisgp.core.negocio.ValidadorControleFinanceiro;
import sisgp.core.negocio.ValidadorGerarContas;
import sisgp.core.negocio.ValidadorLogin;
import sisgp.core.negocio.ValidadorProjeto;
import sisgp.core.negocio.ValidadorRecurso;
import sisgp.core.negocio.ValidadorRecursoHumano;
import sisgp.core.negocio.ValidadorSubAtividade;
import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.Atividade;
import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.CategoriaContasReceber;
import sisgp.dominio.Cliente;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.ContasReceber;
import sisgp.dominio.ControleEstoque;
import sisgp.dominio.ControleFinanceiro;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GerarContas;
import sisgp.dominio.GraficoCategoriaContasPagar;
import sisgp.dominio.GraficoCategoriaContasReceber;
import sisgp.dominio.GraficoContasPagar;
import sisgp.dominio.GraficoContasReceber;
import sisgp.dominio.GrupoAcesso;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Login;
import sisgp.dominio.Nivel;
import sisgp.dominio.Projeto;
import sisgp.dominio.Recurso;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusAtividade;
import sisgp.dominio.StatusContas;
import sisgp.dominio.StatusProjeto;
import sisgp.dominio.StatusSistemaAtividade;
import sisgp.dominio.SubAtividade;

public class Fachada implements InterfaceFachada{
	
	private Map<String, InterfaceDao> mapaDaos;
	private Map<String, Map<String, List<InterfaceStrategy>>> mapaRn;
	private Resultado resultado;
	
	public Fachada() {
		
		mapaDaos = new HashMap<String, InterfaceDao>();
		mapaRn = new HashMap<String, Map<String, List<InterfaceStrategy>>>();
		
		RecursoHumanoDao recursoHumanoDao = new RecursoHumanoDao();
		ProjetoDao projetoDao = new ProjetoDao();
		NivelDao nivelDao = new NivelDao();
		ContasPagarDao contasPagarDao = new ContasPagarDao();
		ContasReceberDao contasReceberDao = new ContasReceberDao();
		CategoriaContasPagarDao catContasPagarDao = new CategoriaContasPagarDao();
		CategoriaContasReceberDao catContasReceberDao = new CategoriaContasReceberDao();
		StatusContasDao statusContasDao = new StatusContasDao();
		StatusAtividadeDao statusAtividadeDao = new StatusAtividadeDao();
		StatusSistemaAtividadeDao stSistAtividadeDao = new StatusSistemaAtividadeDao();
		AtividadeDao atividadeDao = new AtividadeDao();
		SubAtividadeDao subAtividadeDao = new SubAtividadeDao();
		GrupoAcessoDao grupoAcessoDao = new GrupoAcessoDao();
		ApontamentoHorasDao apontamentoHorasDao = new ApontamentoHorasDao();
		StatusProjetoDao statusProjetoDao = new StatusProjetoDao();
		ClienteDao clienteDao = new ClienteDao();
		GerarContasDao gerarContasDao = new GerarContasDao();
		ControleFinanceiroDao controleFinanceiroDao = new ControleFinanceiroDao();
		RecursoDao recursoDao = new RecursoDao();
		ControleEstoqueDao controleEstoqueDao = new ControleEstoqueDao();
		LoginDao loginDao = new LoginDao();
		
		GraficoContasPagarDao graficoContasPagarDao = new GraficoContasPagarDao();
		GraficoContasReceberDao graficoContasReceberDao = new GraficoContasReceberDao();
		GraficoCategoriaContasPagarDao graficoCategoriaContasPagarDao = new GraficoCategoriaContasPagarDao();
		GraficoCategoriaContasReceberDao graficoCategoriaContasReceberDao = new GraficoCategoriaContasReceberDao();
		
		mapaDaos.put(RecursoHumano.class.getName(), recursoHumanoDao);
		mapaDaos.put(Projeto.class.getName(), projetoDao);
		mapaDaos.put(Nivel.class.getName(), nivelDao);
		mapaDaos.put(ContasPagar.class.getName(), contasPagarDao);
		mapaDaos.put(ContasReceber.class.getName(), contasReceberDao);
		mapaDaos.put(CategoriaContasPagar.class.getName(), catContasPagarDao);
		mapaDaos.put(CategoriaContasReceber.class.getName(), catContasReceberDao);
		mapaDaos.put(StatusContas.class.getName(), statusContasDao);
		mapaDaos.put(StatusAtividade.class.getName(), statusAtividadeDao);
		mapaDaos.put(StatusSistemaAtividade.class.getName(), stSistAtividadeDao);
		mapaDaos.put(Atividade.class.getName(), atividadeDao);
		mapaDaos.put(SubAtividade.class.getName(), subAtividadeDao);
		mapaDaos.put(GrupoAcesso.class.getName(), grupoAcessoDao);
		mapaDaos.put(ApontamentoHoras.class.getName(), apontamentoHorasDao);
		mapaDaos.put(StatusProjeto.class.getName(), statusProjetoDao);
		mapaDaos.put(Cliente.class.getName(), clienteDao);
		mapaDaos.put(GerarContas.class.getName(), gerarContasDao);
		mapaDaos.put(ControleFinanceiro.class.getName(), controleFinanceiroDao);
		mapaDaos.put(Recurso.class.getName(), recursoDao);
		mapaDaos.put(ControleEstoque.class.getName(), controleEstoqueDao);
		mapaDaos.put(Login.class.getName(), loginDao);
		
		mapaDaos.put(GraficoCategoriaContasPagar.class.getName(), graficoCategoriaContasPagarDao);
		mapaDaos.put(GraficoCategoriaContasReceber.class.getName(), graficoCategoriaContasReceberDao);
		mapaDaos.put(GraficoContasPagar.class.getName(), graficoContasPagarDao);
		mapaDaos.put(GraficoContasReceber.class.getName(), graficoContasReceberDao);
		
		ValidadorRecursoHumano validadorRecursoHumano = new ValidadorRecursoHumano();
		ValidadorProjeto validadorProjeto = new ValidadorProjeto();
		ValidadorContasPagar validadorContasPagar = new ValidadorContasPagar();
		ValidadorContasReceber validadorContasReceber = new ValidadorContasReceber();
		CalcularGerarContas calcularGerarContas = new CalcularGerarContas();
		ValidadorApontamentoHoras validadorApontamentoHoras = new ValidadorApontamentoHoras();
		ValidadorRecurso validadorRecurso = new ValidadorRecurso();
		ValidadorControleEstoque validadorControleEstoque = new ValidadorControleEstoque();
		ValidadorCliente validadorCliente = new ValidadorCliente();
		ValidadorAtividade validadorAtividade = new ValidadorAtividade();
		ValidadorSubAtividade validadorSubAtividade = new ValidadorSubAtividade();
		ValidadorCategoriaContasPagar validadorCategoriaContasPagar = new ValidadorCategoriaContasPagar();
		ValidadorCategoriaContasReceber validadorCategoriaContasReceber = new ValidadorCategoriaContasReceber();
		ValidadorLogin validadorLogin = new ValidadorLogin();
		ValidadorGerarContas validadorGerarContas = new ValidadorGerarContas();
		ValidadorControleFinanceiro validadorControleFinanceiro = new ValidadorControleFinanceiro();
		
		List<InterfaceStrategy> rnValidadorRecursoHumano = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorProjeto = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorContasPagar = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorContasReceber = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnCalcularGerarContas = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorApontamentoHoras = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorRecurso = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorControleEstoque = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorCliente = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorAtividade = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorSubAtividade = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorCategoriaContasPagar = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorCategoriaContasReceber = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorLogin = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorGerarContas = new ArrayList<InterfaceStrategy>();
		List<InterfaceStrategy> rnValidadorControleFinanceiro = new ArrayList<InterfaceStrategy>();
		
		rnValidadorRecursoHumano.add(validadorRecursoHumano);
		rnValidadorProjeto.add(validadorProjeto);
		rnValidadorContasPagar.add(validadorContasPagar);
		rnValidadorContasReceber.add(validadorContasReceber);
		rnCalcularGerarContas.add(calcularGerarContas);
		rnValidadorApontamentoHoras.add(validadorApontamentoHoras);
		rnValidadorRecurso.add(validadorRecurso);
		rnValidadorControleEstoque.add(validadorControleEstoque);
		rnValidadorCliente.add(validadorCliente);
		rnValidadorAtividade.add(validadorAtividade);
		rnValidadorSubAtividade.add(validadorSubAtividade);
		rnValidadorCategoriaContasPagar.add(validadorCategoriaContasPagar);
		rnValidadorCategoriaContasReceber.add(validadorCategoriaContasReceber);
		rnValidadorLogin.add(validadorLogin);
		rnValidadorGerarContas.add(validadorGerarContas);
		rnValidadorControleFinanceiro.add(validadorControleFinanceiro);
		
		Map<String, List<InterfaceStrategy>> rnRecursoHumano = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnProjeto = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnContasPagar = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnContasReceber = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnGerarContas = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnApontamentoHoras = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnRecurso = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnControleEstoque = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnCliente = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnAtividade = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnSubAtividade = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnCategoriaContasPagar = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnCategoriaContasReceber = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnLogin = new HashMap<String, List<InterfaceStrategy>>();
		Map<String, List<InterfaceStrategy>> rnControleFinanceiro = new HashMap<String, List<InterfaceStrategy>>();
		
		rnRecursoHumano.put("adicionar", rnValidadorRecursoHumano);
		rnRecursoHumano.put("editar", rnValidadorRecursoHumano);
		
		rnProjeto.put("adicionar", rnValidadorProjeto);
		rnProjeto.put("editar", rnValidadorProjeto);
		
		rnContasPagar.put("adicionar", rnValidadorContasPagar);
		rnContasPagar.put("editar", rnValidadorContasPagar);
		
		rnContasReceber.put("adicionar", rnValidadorContasReceber);
		rnContasReceber.put("editar", rnValidadorContasReceber);
		
		rnGerarContas.put("adicionar", rnCalcularGerarContas);
		rnGerarContas.put("consultar", rnValidadorGerarContas);
		
		rnApontamentoHoras.put("adicionar", rnValidadorApontamentoHoras);
		rnApontamentoHoras.put("editar", rnValidadorApontamentoHoras);
		
		rnRecurso.put("adicionar", rnValidadorRecurso);
		rnRecurso.put("editar", rnValidadorRecurso);
		
		rnControleEstoque.put("adicionar", rnValidadorControleEstoque);
		rnControleEstoque.put("editar", rnValidadorControleEstoque);
		
		rnCliente.put("adicionar", rnValidadorCliente);
		rnCliente.put("editar", rnValidadorCliente);
		
		rnAtividade.put("adicionar", rnValidadorAtividade);
		rnAtividade.put("editar", rnValidadorAtividade);
		
		rnSubAtividade.put("adicionar", rnValidadorSubAtividade);
		rnSubAtividade.put("editar", rnValidadorSubAtividade);
		
		rnCategoriaContasPagar.put("adicionar", rnValidadorCategoriaContasPagar);
		rnCategoriaContasPagar.put("editar", rnValidadorCategoriaContasPagar);
		
		rnCategoriaContasReceber.put("adicionar", rnValidadorCategoriaContasReceber);
		rnCategoriaContasReceber.put("editar", rnValidadorCategoriaContasReceber);
		
		rnLogin.put("consultar", rnValidadorLogin);
		
		rnControleFinanceiro.put("consultar", rnValidadorControleFinanceiro);
		
		mapaRn.put(RecursoHumano.class.getName(), rnRecursoHumano);
		mapaRn.put(Projeto.class.getName(), rnProjeto);
		mapaRn.put(ContasPagar.class.getName(), rnContasPagar);
		mapaRn.put(ContasReceber.class.getName(), rnContasReceber);
		mapaRn.put(GerarContas.class.getName(), rnGerarContas);
		mapaRn.put(ApontamentoHoras.class.getName(), rnApontamentoHoras);
		mapaRn.put(Recurso.class.getName(), rnRecurso);
		mapaRn.put(ControleEstoque.class.getName(), rnControleEstoque);
		mapaRn.put(Cliente.class.getName(), rnCliente);
		mapaRn.put(Atividade.class.getName(), rnAtividade);
		mapaRn.put(SubAtividade.class.getName(), rnSubAtividade);
		mapaRn.put(CategoriaContasPagar.class.getName(), rnCategoriaContasPagar);
		mapaRn.put(CategoriaContasReceber.class.getName(), rnCategoriaContasReceber);
		mapaRn.put(Login.class.getName(), rnLogin);
		mapaRn.put(ControleFinanceiro.class.getName(), rnControleFinanceiro);
	}

	@Override
	public Resultado adicionar(EntidadeDominio entidade) {

		resultado = new Resultado();
		
		String nomeClasse = entidade.getClass().getName();
		
		String mensagem = executarRegras(entidade, "adicionar");
		
		if(mensagem == null) {
			InterfaceDao dao = mapaDaos.get(nomeClasse);
			
			dao.adicionar(entidade);
			List<EntidadeDominio> listaEntidades = new ArrayList<EntidadeDominio>();
			listaEntidades.add(0,entidade);
			resultado.setListaEntidade(listaEntidades);
			
			//resultado.setMensagem(mensagem);

		}else {
			resultado.setMensagem(mensagem);
		}
		
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio entidade) {
		
		resultado = new Resultado();
		
		String nomeClasse = entidade.getClass().getName();
		
		String mensagem = executarRegras(entidade, "editar");
		
		if(mensagem == null) {
			InterfaceDao dao = mapaDaos.get(nomeClasse);
			
			dao.editar(entidade);
			List<EntidadeDominio> listaEntidade = new ArrayList<EntidadeDominio>();
			listaEntidade.add(0,entidade);
			resultado.setListaEntidade(listaEntidade);
			
			//resultado.setMensagem(mensagem);
		}else {
			resultado.setMensagem(mensagem);
		}
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {

		resultado = new Resultado();
		
		String nomeClasse = entidade.getClass().getName();
		
		InterfaceDao dao = mapaDaos.get(nomeClasse);
		dao.excluir(entidade);
		List<EntidadeDominio> listaEntidade = new ArrayList<EntidadeDominio>();
		listaEntidade.add(0,entidade);
		
		return resultado;
	}

	@Override
	public Resultado consultar(InterfaceEntidade entidade) {
		
		resultado = new Resultado();
		
		String nomeClasse = entidade.getClass().getName();
		
		String mensagem = executarRegras(entidade, "consultar");//
		if(mensagem == null) {//
			InterfaceDao dao = mapaDaos.get(nomeClasse);
			
			resultado.setListaEntidade(dao.consultar(entidade));
		}else {//
			resultado.setMensagem(mensagem);//
		}//
		return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setListaEntidade(new ArrayList<EntidadeDominio>(1));
		resultado.getListaEntidade().add(entidade);
		return resultado;
	}
	
	
	private String executarRegras(InterfaceEntidade entidade, String operacao) {
		String nomeClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<InterfaceStrategy>> regrasOperacao = mapaRn.get(nomeClasse);
		
		if(regrasOperacao != null) {
			List<InterfaceStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null) {
				for(InterfaceStrategy s: regras) {
					String m = s.processar(entidade);
					
					if(m != null) {
						msg.append(m);
						msg.append("\n");
					}
					
				}
			}
			
		}
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}

}


