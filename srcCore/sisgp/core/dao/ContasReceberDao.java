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
import sisgp.dominio.CategoriaContasReceber;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.ContasReceber;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.StatusContas;

public class ContasReceberDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ContasReceber cr = (ContasReceber)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into contas_receber (id_cat_contas_receber,");
		sql.append("id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,");
		sql.append("contrato,observacao,id_projeto,");
		sql.append("st_ativo) values (?,?,?,?,?,?,?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			cr.setStAtivo(true);
			
			if(cr.getCategoriaContasReceber() == null) {
				pst.setNull(1, Types.INTEGER);
			}else {
				pst.setInt(1, cr.getCategoriaContasReceber().getId());
			}			
			pst.setInt(2, cr.getStatusContas().getId());
			pst.setDate(3, (Date) cr.getDtLancamento());//talvez precise remover
			pst.setDate(4, (Date) cr.getDtVencimento());
			pst.setDouble(5, cr.getValor());
			pst.setInt(6, cr.getNrParcelas());
			pst.setString(7, cr.getContrato());
			pst.setString(8, cr.getObservacao());			
			if(cr.getProjeto() == null) {
				pst.setNull(9, Types.INTEGER);
			}else {
				pst.setInt(9, cr.getProjeto().getId());
			}
			
			pst.setBoolean(10, cr.isStAtivo());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(cr.getDtVencimento());
			
			int i = 0;
			
			for(i = 0; i < cr.getNrParcelas(); i++) {
				pst.executeUpdate();
												
				calendar.add(Calendar.MONTH, 1);
				
				pst.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));

			}
			
			
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				cr.setId(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ContasReceber cr = (ContasReceber)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update contas_receber set id_cat_contas_receber = ?,");
		sql.append("id_status_conta = ?,dt_lancamento = ?,dt_vencimento = ?,valor = ?,nr_parcelas = ?,");
		sql.append("contrato = ?,observacao = ?,id_projeto = ?,");
		sql.append("st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			cr.setStAtivo(true);
			
			if(cr.getCategoriaContasReceber() == null) {
				pst.setNull(1, Types.INTEGER);
			}else {
				pst.setInt(1, cr.getCategoriaContasReceber().getId());
			}
			pst.setInt(2, cr.getStatusContas().getId());
			pst.setDate(3, (Date) cr.getDtLancamento());//talvez precise remover
			pst.setDate(4, (Date) cr.getDtVencimento());
			pst.setDouble(5, cr.getValor());
			pst.setInt(6, cr.getNrParcelas());
			pst.setString(7, cr.getContrato());
			pst.setString(8, cr.getObservacao());
			if(cr.getProjeto() == null) {
				pst.setNull(9, Types.INTEGER);
			}else {
				pst.setInt(9, cr.getProjeto().getId());
			}
			pst.setBoolean(10, cr.isStAtivo());
			
			pst.setInt(11, cr.getId());
			
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ContasReceber cr = (ContasReceber)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update contas_receber set st_ativo = ? ");
		sql.append("where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			cr.setStAtivo(false);
			
			
			pst.setBoolean(1, cr.isStAtivo());
			
			pst.setInt(2, cr.getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaContasReceber = new ArrayList<EntidadeDominio>();
		
		ContasReceber crConsulta = (ContasReceber)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from contas_receber where st_ativo = 1 ");
		
		if(crConsulta.getId() != null && crConsulta.getId() != 0) {
			sql.append(" and id = ");
			sql.append(crConsulta.getId());
		}
		
		if(crConsulta.getCategoriaContasReceber() != null) {
			sql.append(" and id_cat_contas_receber = ");
			sql.append(crConsulta.getCategoriaContasReceber().getId());
		}
		
		if(crConsulta.getStatusContas() != null) {
			sql.append(" and id_status_conta = ");
			sql.append(crConsulta.getStatusContas().getId());
		}
		
		if(crConsulta.getDtLancamento() != null) {
			sql.append(" and dt_lancamento = ");
			sql.append(crConsulta.getDtLancamento());
		}
		
		if(crConsulta.getDtVencimento() != null) {
			sql.append(" and dt_vencimento = ");
			sql.append(crConsulta.getDtVencimento());
		}
		
		if(crConsulta.getValor() != null && crConsulta.getValor() != 0) {
			sql.append(" and valor = ");
			sql.append(crConsulta.getValor());
		}
		
		if(crConsulta.getNrParcelas() != null && crConsulta.getNrParcelas() != 0) {
			sql.append(" and nr_parcelas = ");
			sql.append(crConsulta.getNrParcelas());
		}
		
		if(crConsulta.getContrato() != null && !crConsulta.getContrato().trim().equals("")) {
			sql.append(" and contrato like '%");
			sql.append(crConsulta.getContrato());
			sql.append("%'");
		}
		
		if(crConsulta.getProjeto() != null) {
			sql.append(" and id_projeto = ");
			sql.append(crConsulta.getProjeto().getId());
		}
		

		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				ContasReceber cr = new ContasReceber();
				
				cr.setId(rs.getInt("id"));
				
				Integer idCategoria = rs.getInt("id_cat_contas_receber");
				CategoriaContasReceber ccp = new CategoriaContasReceber(idCategoria);
				cr.setCategoriaContasReceber(ccp);
				
				Integer idStatus = rs.getInt("id_status_conta");
				StatusContas sc = new StatusContas(idStatus);
				cr.setStatusContas(sc);
							
				cr.setDtLancamento(rs.getDate("dt_lancamento"));
				cr.setDtVencimento(rs.getDate("dt_vencimento"));
				cr.setValor(rs.getDouble("valor"));
				cr.setNrParcelas(rs.getInt("nr_parcelas"));
				cr.setContrato(rs.getString("contrato"));
				cr.setObservacao(rs.getString("observacao"));
				
				Integer idProjeto = rs.getInt("id_projeto");
				Projeto projeto = new Projeto(idProjeto);
				cr.setProjeto(projeto);
				
				cr.setStAtivo(rs.getBoolean("st_ativo"));
				
				listaContasReceber.add(cr);
			}
			
			return listaContasReceber;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
