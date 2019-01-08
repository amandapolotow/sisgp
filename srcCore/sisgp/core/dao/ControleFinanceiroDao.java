package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.CategoriaContasReceber;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.ContasReceber;
import sisgp.dominio.ControleFinanceiro;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.StatusContas;

public class ControleFinanceiroDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		PreparedStatement pst4 = null;
		List<EntidadeDominio> listaControle = new ArrayList<EntidadeDominio>();
		List<CategoriaContasPagar> listaCatContasPagar = new ArrayList<CategoriaContasPagar>();
		List<CategoriaContasReceber> listaCatContasReceber = new ArrayList<CategoriaContasReceber>();
		ControleFinanceiro controle = new ControleFinanceiro();
		
		ControleFinanceiro ctrlConsulta = (ControleFinanceiro)entidade;
		
		StringBuilder sql = new StringBuilder(); 
		
		
		//selecionar despesa
		sql.append("select sum(valor) as despesa ");
		sql.append(" from contas_pagar where st_ativo = 1 ");
		
		if(ctrlConsulta.getDtDe() != null && ctrlConsulta.getDtAte() != null) {
			sql.append(" and date_format(dt_vencimento,'%Y-%m-%d') between str_to_date('");
			sql.append(ctrlConsulta.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(ctrlConsulta.getDtAte());
			sql.append("','%Y-%m-%d')");
		}
		
		if(ctrlConsulta.getStatusContas() != null && ctrlConsulta.getStatusContas().getId() != null && ctrlConsulta.getStatusContas().getId() != 0) {
			sql.append(" and id_status_conta = ");
			sql.append(ctrlConsulta.getStatusContas().getId());			
		}
		
		if(ctrlConsulta.getProjeto() != null) {
			sql.append(" and id_projeto = ");
			sql.append(ctrlConsulta.getProjeto().getId());			
		}
		
		System.out.println("Consulta1: " + sql.toString());
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				controle.setDespesa(rs.getDouble("despesa"));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql.setLength(0);
		//selecionar receita
		sql.append("select sum(valor) as receita ");
		sql.append(" from contas_receber where st_ativo = 1 ");
		
		if(ctrlConsulta.getDtDe() != null && ctrlConsulta.getDtAte() != null) {
			sql.append(" and date_format(dt_vencimento,'%Y-%m-%d') between str_to_date('");
			sql.append(ctrlConsulta.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(ctrlConsulta.getDtAte());
			sql.append("','%Y-%m-%d')");
		}
		
		if(ctrlConsulta.getStatusContas() != null) {
			sql.append(" and id_status_conta = ");
			sql.append(ctrlConsulta.getStatusContas().getId());			
		}
		
		if(ctrlConsulta.getProjeto() != null) {
			sql.append(" and id_projeto = ");
			sql.append(ctrlConsulta.getProjeto().getId());			
		}
		
		System.out.println("Consulta2: " + sql.toString());
		
		abrirConexao();
		
		try {
			pst2 = conexao.prepareStatement(sql.toString());
			
			ResultSet rs2 = pst2.executeQuery();
			
			while (rs2.next()) {
				controle.setReceita(rs2.getDouble("receita"));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql.setLength(0);
		
		//despesas por categoria
		sql.append("select sum(contas_pagar.valor) as valor ");
		sql.append(",contas_pagar.id_cat_contas_pagar ");
		sql.append(",categoria_contas_pagar.categoria ");
		sql.append("from contas_pagar ");
		sql.append("inner join categoria_contas_pagar ");
		sql.append("on contas_pagar.id_cat_contas_pagar = categoria_contas_pagar.id ");
		sql.append("where contas_pagar.st_ativo = 1 ");
		
		if(ctrlConsulta.getDtDe() != null && ctrlConsulta.getDtAte() != null) {
			sql.append(" and date_format(contas_pagar.dt_vencimento,'%Y-%m-%d') between str_to_date('");
			sql.append(ctrlConsulta.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(ctrlConsulta.getDtAte());
			sql.append("','%Y-%m-%d')");
		}
		if(ctrlConsulta.getStatusContas() != null) {
			sql.append(" and contas_pagar.id_status_conta = ");
			sql.append(ctrlConsulta.getStatusContas().getId());			
		}
		if(ctrlConsulta.getProjeto() != null) {
			sql.append(" and contas_pagar.id_projeto = ");
			sql.append(ctrlConsulta.getProjeto().getId());			
		}
		
		sql.append(" group by contas_pagar.id_cat_contas_pagar ");
		
		System.out.println("Consulta3: " + sql.toString());
		
		abrirConexao();
		
		try {
			pst3 = conexao.prepareStatement(sql.toString());
			
			ResultSet rs3 = pst3.executeQuery();
			
			while (rs3.next()) {
				CategoriaContasPagar ccp = new CategoriaContasPagar();
				
				ccp.setId(rs3.getInt("id_cat_contas_pagar"));
				ccp.setCategoria(rs3.getString("categoria"));
				ccp.setValor(rs3.getDouble("valor"));
				
				listaCatContasPagar.add(ccp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql.setLength(0);
		
		//receita por categoria
		sql.append("select sum(contas_receber.valor) as valor ");
		sql.append(",contas_receber.id_cat_contas_receber ");
		sql.append(",categoria_contas_receber.categoria ");
		sql.append("from contas_receber ");
		sql.append("inner join categoria_contas_receber ");
		sql.append("on contas_receber.id_cat_contas_receber = categoria_contas_receber.id ");
		sql.append("where contas_receber.st_ativo = 1 ");
		
		if(ctrlConsulta.getDtDe() != null && ctrlConsulta.getDtAte() != null) {
			sql.append(" and date_format(contas_receber.dt_vencimento,'%Y-%m-%d') between str_to_date('");
			sql.append(ctrlConsulta.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(ctrlConsulta.getDtAte());
			sql.append("','%Y-%m-%d')");
		}
		if(ctrlConsulta.getStatusContas() != null) {
			sql.append(" and contas_receber.id_status_conta = ");
			sql.append(ctrlConsulta.getStatusContas().getId());			
		}
		if(ctrlConsulta.getProjeto() != null) {
			sql.append(" and contas_receber.id_projeto = ");
			sql.append(ctrlConsulta.getProjeto().getId());			
		}
		
		sql.append(" group by contas_receber.id_cat_contas_receber ");
		
		System.out.println("Consulta4: " + sql.toString());
		
		abrirConexao();
		
		try {
			pst4 = conexao.prepareStatement(sql.toString());
			
			ResultSet rs4 = pst4.executeQuery();
			
			while (rs4.next()) {
				CategoriaContasReceber ccr = new CategoriaContasReceber();
				
				ccr.setId(rs4.getInt("id_cat_contas_receber"));
				ccr.setCategoria(rs4.getString("categoria"));
				ccr.setValor(rs4.getDouble("valor"));
				
				listaCatContasReceber.add(ccr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		controle.setListaCategoriaContasPagar(listaCatContasPagar);
		controle.setListaCategoriaContasReceber(listaCatContasReceber);
		
		//Setando os filtros
		if(ctrlConsulta.getStatusContas() != null && ctrlConsulta.getStatusContas().getId() != null && ctrlConsulta.getStatusContas().getId() != 0) {
			controle.setStatusContas(ctrlConsulta.getStatusContas());			
		}
		if(ctrlConsulta.getProjeto() != null && ctrlConsulta.getProjeto().getId() != null && ctrlConsulta.getProjeto().getId() != 0) {
			controle.setProjeto(ctrlConsulta.getProjeto());			
		}
		if(ctrlConsulta.getDtDe() != null && ctrlConsulta.getDtAte() != null) {
			controle.setDtDe(ctrlConsulta.getDtDe());
			controle.setDtAte(ctrlConsulta.getDtAte());
		}
		
		
		listaControle.add(controle);
		
		return listaControle;
	}

}
