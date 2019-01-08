package sisgp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.web.command.AdicionarCommand;
import sisgp.web.command.ConsultarCommand;
import sisgp.web.command.EditarCommand;
import sisgp.web.command.ExcluirCommand;
import sisgp.web.command.VisualizarCommand;
import sisgp.web.interfaces.InterfaceCommand;
import sisgp.web.interfaces.InterfaceViewHelper;
import sisgp.web.viewhelper.ApontamentoHorasLista1ViewHelper;
import sisgp.web.viewhelper.ApontamentoHorasLista2ViewHelper;
import sisgp.web.viewhelper.ApontamentoHorasViewHelper;
import sisgp.web.viewhelper.AtividadeViewHelper;
import sisgp.web.viewhelper.CategoriaContasPagarViewHelper;
import sisgp.web.viewhelper.CategoriaContasReceberViewHelper;
import sisgp.web.viewhelper.ClienteViewHelper;
import sisgp.web.viewhelper.ConsultarAtividadeViewHelper;
import sisgp.web.viewhelper.ConsultarCatCtPagarViewHelper;
import sisgp.web.viewhelper.ConsultarCatCtReceberViewHelper;
import sisgp.web.viewhelper.ConsultarClienteViewHelper;
import sisgp.web.viewhelper.ConsultarGestorViewHelper;
import sisgp.web.viewhelper.ConsultarProjetoViewHelper;
import sisgp.web.viewhelper.ConsultarRecursoHumanoViewHelper;
import sisgp.web.viewhelper.ConsultarRecursoViewHelper;
import sisgp.web.viewhelper.ConsultarSubAtividadeViewHelper;
import sisgp.web.viewhelper.ContasPagarViewHelper;
import sisgp.web.viewhelper.ContasReceberViewHelper;
import sisgp.web.viewhelper.ControleEstoqueViewHelper;
import sisgp.web.viewhelper.ControleFinanceiroViewHelper;
import sisgp.web.viewhelper.ControleHorasViewHelper;
import sisgp.web.viewhelper.GerarContasViewHelper;
import sisgp.web.viewhelper.GraficoCategoriaContasPagarViewHelper;
import sisgp.web.viewhelper.GraficoCategoriaContasReceberViewHelper;
import sisgp.web.viewhelper.GraficoContasPagarViewHelper;
import sisgp.web.viewhelper.GraficoContasReceberViewHelper;
import sisgp.web.viewhelper.GrupoAcessoViewHelper;
import sisgp.web.viewhelper.GsonCategoriaContasPagarViewHelper;
import sisgp.web.viewhelper.GsonCategoriaContasReceberViewHelper;
import sisgp.web.viewhelper.GsonContasPagarViewHelper;
import sisgp.web.viewhelper.GsonContasReceberViewHelper;
import sisgp.web.viewhelper.HomeViewHelper;
import sisgp.web.viewhelper.IndexViewHelper;
import sisgp.web.viewhelper.LoginViewHelper;
import sisgp.web.viewhelper.NivelViewHelper;
import sisgp.web.viewhelper.ProjetoViewHelper;
import sisgp.web.viewhelper.RecursoHumanoViewHelper;
import sisgp.web.viewhelper.RecursoViewHelper;
import sisgp.web.viewhelper.StatusAtividadeViewHelper;
import sisgp.web.viewhelper.StatusContasViewHelper;
import sisgp.web.viewhelper.StatusProjetoViewHelper;
import sisgp.web.viewhelper.StatusSistemaAtividadeViewHelper;
import sisgp.web.viewhelper.SubAtividadeViewHelper;




public class Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Map<String, InterfaceCommand> mapaCommands;
	private static Map<String, InterfaceViewHelper> mapaViewHelpers;
	private static String uri = null;
	private static HttpServletRequest request;
	private static String operacao = null;
	
	private static InterfaceViewHelper vh;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		Enumeration<String> parametros = config.getInitParameterNames();
		
		while(parametros.hasMoreElements()) {
			String parametro = parametros.nextElement();
			uri = config.getInitParameter(parametro);
			Resultado resultado = doProcess();
			
			vh.setView(resultado,config);
		}
		
		
	}
	
	public Servlet() {
		
        mapaCommands = new HashMap<String, InterfaceCommand>();
        mapaCommands.put("adicionar", new AdicionarCommand());
        mapaCommands.put("editar", new EditarCommand());
        mapaCommands.put("excluir", new ExcluirCommand());
        mapaCommands.put("visualizar", new VisualizarCommand());
        
        ConsultarCommand consultarCmd = new ConsultarCommand();
        //
        mapaCommands.put("consultar", consultarCmd);
      
        //
        
        
        mapaViewHelpers = new HashMap<String, InterfaceViewHelper>();
        
        mapaViewHelpers.put("/sisgp/", new IndexViewHelper());
        mapaViewHelpers.put("/sisgp/Home", new HomeViewHelper());
        
        //consteudo para listas/combos
        mapaViewHelpers.put("/sisgp/ConsultarNivel", new NivelViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarGrupoAcesso", new GrupoAcessoViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarStatusContas", new StatusContasViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarCategoriaContasPagar", new ConsultarCatCtPagarViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarCategoriaContasReceber", new ConsultarCatCtReceberViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarRecursosHumanos", new ConsultarRecursoHumanoViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarSubAtividade", new ConsultarSubAtividadeViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarAtividade", new ConsultarAtividadeViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarProjetos", new ConsultarProjetoViewHelper());
        mapaViewHelpers.put("/sisgp/StatusSistemaAtividade", new StatusSistemaAtividadeViewHelper());
        mapaViewHelpers.put("/sisgp/StatusAtividade", new StatusAtividadeViewHelper());
        mapaViewHelpers.put("/sisgp/StatusProjeto", new StatusProjetoViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarRecurso", new ConsultarRecursoViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarGestor", new ConsultarGestorViewHelper());
        mapaViewHelpers.put("/sisgp/ConsultarClientes", new ConsultarClienteViewHelper());
        //paginas
        mapaViewHelpers.put("/sisgp/ContasPagar", new ContasPagarViewHelper());
        mapaViewHelpers.put("/sisgp/ContasReceber", new ContasReceberViewHelper());
        mapaViewHelpers.put("/sisgp/RecursosHumanos", new RecursoHumanoViewHelper());
        mapaViewHelpers.put("/sisgp/Projetos", new ProjetoViewHelper());  
        mapaViewHelpers.put("/sisgp/ApontamentoHoras", new ApontamentoHorasViewHelper());
        mapaViewHelpers.put("/sisgp/SubAtividades", new SubAtividadeViewHelper());
        mapaViewHelpers.put("/sisgp/Atividades", new AtividadeViewHelper());
        mapaViewHelpers.put("/sisgp/Clientes", new ClienteViewHelper());
        mapaViewHelpers.put("/sisgp/ApontamentoHorasLista2", new ApontamentoHorasLista2ViewHelper());
        mapaViewHelpers.put("/sisgp/ApontamentoHorasLista1", new ApontamentoHorasLista1ViewHelper());
        mapaViewHelpers.put("/sisgp/GerarContas", new GerarContasViewHelper());
        mapaViewHelpers.put("/sisgp/ControleHoras", new ControleHorasViewHelper());
        mapaViewHelpers.put("/sisgp/ControleFinanceiro", new ControleFinanceiroViewHelper());
        mapaViewHelpers.put("/sisgp/CategoriaContasPagar", new CategoriaContasPagarViewHelper());
        mapaViewHelpers.put("/sisgp/CategoriaContasReceber", new CategoriaContasReceberViewHelper());
        mapaViewHelpers.put("/sisgp/Recursos", new RecursoViewHelper());
        mapaViewHelpers.put("/sisgp/ControleEstoque", new ControleEstoqueViewHelper());
        mapaViewHelpers.put("/sisgp/Login", new LoginViewHelper());
        //Graficos
        mapaViewHelpers.put("/sisgp/GraficoContasPagar", new GraficoContasPagarViewHelper());
        mapaViewHelpers.put("/sisgp/GraficoContasReceber", new GraficoContasReceberViewHelper());
        mapaViewHelpers.put("/sisgp/GsonContasPagar", new GsonContasPagarViewHelper());
        mapaViewHelpers.put("/sisgp/GsonContasReceber", new GsonContasReceberViewHelper());
        
        mapaViewHelpers.put("/sisgp/GraficoCategoriaContasPagar", new GraficoCategoriaContasPagarViewHelper());
        mapaViewHelpers.put("/sisgp/GraficoCategoriaContasReceber", new GraficoCategoriaContasReceberViewHelper());
        mapaViewHelpers.put("/sisgp/GsonCategoriaContasPagar", new GsonCategoriaContasPagarViewHelper());
        mapaViewHelpers.put("/sisgp/GsonCategoriaContasReceber", new GsonCategoriaContasReceberViewHelper());
 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcessRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcessRequest(request, response);
	}
	
	protected void doProcessRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		
		uri = request.getRequestURI();

		this.request = request;
		
		operacao = request.getParameter("operacao");
		
		Resultado resultado = doProcess();
		
		vh.setView(resultado,request,response);
	
	
	}
	
	protected Resultado doProcess() throws ServletException {
		
		vh = mapaViewHelpers.get(uri);
		InterfaceEntidade entidade = vh.getEntidade(request);
		
		if(request == null) {
			operacao = "consultar";
		}else {
			operacao = request.getParameter("operacao");
		}
		
		if(operacao != null) {
			InterfaceCommand command = mapaCommands.get(operacao);
			
			Resultado resultado = command.executar(entidade);
			
			return resultado;
		}
		
		
		return null;
		
	}

}
