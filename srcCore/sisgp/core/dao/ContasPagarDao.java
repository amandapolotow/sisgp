package sisgp.core.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.StatusContas;

public class ContasPagarDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ContasPagar cp = (ContasPagar)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into contas_pagar (id_cat_contas_pagar,");
		sql.append("id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,");
		sql.append("contrato,observacao,id_projeto,");
		sql.append("st_ativo) values (?,?,?,?,?,?,?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			cp.setStAtivo(true);
			
			if(cp.getCategoriaContasPagar() == null) {
				pst.setNull(1, Types.INTEGER);
			}else {
				pst.setInt(1, cp.getCategoriaContasPagar().getId());
			}			
			pst.setInt(2, cp.getStatusContas().getId());
			pst.setDate(3, (Date) cp.getDtLancamento());//talvez precise remover
			pst.setDate(4, (Date) cp.getDtVencimento());
			pst.setDouble(5, cp.getValor());
			pst.setInt(6, cp.getNrParcelas());
			pst.setString(7, cp.getContrato());
			pst.setString(8, cp.getObservacao());			
			if(cp.getProjeto() == null) {
				pst.setNull(9, Types.INTEGER);
			}else {
				pst.setInt(9, cp.getProjeto().getId());
			}
			
			pst.setBoolean(10, cp.isStAtivo());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(cp.getDtVencimento());
			
			int i = 0;
			
			for(i = 0; i < cp.getNrParcelas(); i++) {
				pst.executeUpdate();
												
				calendar.add(Calendar.MONTH, 1);
				
				pst.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));

			}
			
			
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				cp.setId(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ContasPagar cp = (ContasPagar)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update contas_pagar set id_cat_contas_pagar = ?,");
		sql.append("id_status_conta = ?,dt_lancamento = ?,dt_vencimento = ?,valor = ?,nr_parcelas = ?,");
		sql.append("contrato = ?,observacao = ?,id_projeto = ?,");
		sql.append("st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			cp.setStAtivo(true);
			
			if(cp.getCategoriaContasPagar() == null) {
				pst.setNull(1, Types.INTEGER);
			}else {
				pst.setInt(1, cp.getCategoriaContasPagar().getId());
			}
			pst.setInt(2, cp.getStatusContas().getId());
			pst.setDate(3, (Date) cp.getDtLancamento());//talvez precise remover
			pst.setDate(4, (Date) cp.getDtVencimento());
			pst.setDouble(5, cp.getValor());
			pst.setInt(6, cp.getNrParcelas());
			pst.setString(7, cp.getContrato());
			pst.setString(8, cp.getObservacao());
			if(cp.getProjeto() == null) {
				pst.setNull(9, Types.INTEGER);
			}else {
				pst.setInt(9, cp.getProjeto().getId());
			}
			pst.setBoolean(10, cp.isStAtivo());
			
			pst.setInt(11, cp.getId());
			
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Contas referentes a pagamento de horas
		if(cp.getStatusContas().getId() == 1) {
			StringBuilder sql2 = new StringBuilder();
			PreparedStatement pst2 = null;
			
			sql2.append("update apontamento_horas set st_pago = 1 ");
			sql2.append(" where id_conta_pagar = ? ");
			
			
			try {
				pst2 = conexao.prepareStatement(sql2.toString());
				
				pst2.setInt(1, cp.getId());
				
				pst2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ContasPagar cp = (ContasPagar)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update contas_pagar set st_ativo = ? ");
		sql.append("where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			cp.setStAtivo(false);
			
			
			pst.setBoolean(1, cp.isStAtivo());
			
			pst.setInt(2, cp.getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaContasPagar = new ArrayList<EntidadeDominio>();
		
		ContasPagar cpConsulta = (ContasPagar)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from contas_pagar where st_ativo = 1 ");
		
		if(cpConsulta.getId() != null && cpConsulta.getId() != 0) {
			sql.append(" and id = ");
			sql.append(cpConsulta.getId());
		}
		
		if(cpConsulta.getCategoriaContasPagar() != null) {
			sql.append(" and id_cat_contas_pagar = ");
			sql.append(cpConsulta.getCategoriaContasPagar().getId());
		}
		
		if(cpConsulta.getStatusContas() != null) {
			sql.append(" and id_status_conta = ");
			sql.append(cpConsulta.getStatusContas().getId());
		}
		
		if(cpConsulta.getDtLancamento() != null) {
			sql.append(" and dt_lancamento = str_to_date('");
			sql.append(cpConsulta.getDtLancamento());
			sql.append("','%Y-%m-%d')");
		}
		
		if(cpConsulta.getDtVencimento() != null) {
			sql.append(" and dt_vencimento = str_to_date('");
			sql.append(cpConsulta.getDtVencimento());
			sql.append("','%Y-%m-%d')");
		}
		
		if(cpConsulta.getValor() != null && cpConsulta.getValor() != 0) {
			sql.append(" and valor = ");
			sql.append(cpConsulta.getValor());
		}
		
		if(cpConsulta.getNrParcelas() != null && cpConsulta.getNrParcelas() != 0) {
			sql.append(" and nr_parcelas = ");
			sql.append(cpConsulta.getNrParcelas());
		}
		
		if(cpConsulta.getContrato() != null && !cpConsulta.getContrato().trim().equals("")) {
			sql.append(" and contrato like '%");
			sql.append(cpConsulta.getContrato());
			sql.append("%'");
		}
		
		if(cpConsulta.getProjeto() != null) {
			sql.append(" and id_projeto = ");
			sql.append(cpConsulta.getProjeto().getId());
		}
		
		System.out.println("Contas a Pagar: " + sql.toString());
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				ContasPagar cp = new ContasPagar();
				
				cp.setId(rs.getInt("id"));
				
				Integer idCategoria = rs.getInt("id_cat_contas_pagar");
				CategoriaContasPagar ccp = new CategoriaContasPagar(idCategoria);
				cp.setCategoriaContasPagar(ccp);
				
				Integer idStatus = rs.getInt("id_status_conta");
				StatusContas sc = new StatusContas(idStatus);
				cp.setStatusContas(sc);
							
				cp.setDtLancamento(rs.getDate("dt_lancamento"));
				cp.setDtVencimento(rs.getDate("dt_vencimento"));
				cp.setValor(rs.getDouble("valor"));
				cp.setNrParcelas(rs.getInt("nr_parcelas"));
				cp.setContrato(rs.getString("contrato"));
				cp.setObservacao(rs.getString("observacao"));
				
				Integer idProjeto = rs.getInt("id_projeto");
				Projeto projeto = new Projeto(idProjeto);
				cp.setProjeto(projeto);
				
				cp.setStAtivo(rs.getBoolean("st_ativo"));
				
				listaContasPagar.add(cp);
			}
			
			return listaContasPagar;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
