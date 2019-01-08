package sisgp.core.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.SubAtividade;

public class ApontamentoHorasDao extends AbstractDao {

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ApontamentoHoras ah = (ApontamentoHoras)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into apontamento_horas (id_sub_atividade,");
		sql.append("nr_horas,nr_horas_extras,data,id_conta_pagar,");
		sql.append("st_pago,st_ativo) values (?,?,?,?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ah.setStAtivo(true);
			ah.setStPago(false);
			
			pst.setInt(1, ah.getSubAtividade().getId());	
			
			if(ah.getNrHoras() == null) {
				pst.setNull(2, Types.INTEGER);
			}else {
				pst.setInt(2, ah.getNrHoras());
			}			
			if(ah.getNrHorasExtras() == null) {
				pst.setNull(3, Types.INTEGER);
			}else {
				pst.setInt(3, ah.getNrHorasExtras());
			}	
			
			pst.setDate(4, (Date) ah.getData());
			
			if(ah.getContasPagar() == null) {
				pst.setNull(5, Types.DATE);
			}else {
				pst.setInt(5, ah.getContasPagar().getId());
			}			

			pst.setBoolean(6, ah.getStPago());						
			pst.setBoolean(7, ah.isStAtivo());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				ah.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ApontamentoHoras ah = (ApontamentoHoras)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update apontamento_horas set id_sub_atividade = ?,");
		sql.append("nr_horas = ?,nr_horas_extras = ?,data = ?,id_conta_pagar = ?,");
		sql.append("st_pago = ?,st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ah.setStAtivo(true);
			
			pst.setInt(1, ah.getSubAtividade().getId());						
			if(ah.getNrHoras() == null) {
				pst.setNull(2, Types.INTEGER);
			}else {
				pst.setInt(2, ah.getNrHoras());
			}			
			if(ah.getNrHorasExtras() == null) {
				pst.setNull(3, Types.INTEGER);
			}else {
				pst.setInt(3, ah.getNrHorasExtras());
			}			
			pst.setDate(4, (Date) ah.getData());			
			if(ah.getContasPagar() == null) {
				pst.setNull(5, Types.DATE);
			}else {
				pst.setInt(5, ah.getContasPagar().getId());
			}			
			if(ah.getStPago() == null) {
				ah.setStPago(false);
			}
			pst.setBoolean(6, ah.getStPago());
						
			pst.setBoolean(7, ah.isStAtivo());
			
			pst.setInt(8, ah.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ApontamentoHoras ah = (ApontamentoHoras)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update apontamento_horas set ");
		sql.append("st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ah.setStAtivo(false);
						
			pst.setBoolean(1, ah.isStAtivo());
			
			pst.setInt(2, ah.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaApontamentos = new ArrayList<EntidadeDominio>();
		
		ApontamentoHoras ConsultaAH = (ApontamentoHoras) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from apontamento_horas ");
		sql.append("inner join sub_atividades ");
		sql.append("on apontamento_horas.id_sub_atividade = sub_atividades.id ");
		sql.append(" where apontamento_horas.st_ativo = 1 ");
		
		if(ConsultaAH.getId() != null && ConsultaAH.getId() != 0) {
			sql.append(" and apontamento_horas.id = ");
			sql.append(ConsultaAH.getId());
		}
		
		if(ConsultaAH.getContasPagar() != null && ConsultaAH.getContasPagar().getId() != null && ConsultaAH.getContasPagar().getId() != 0) {
			sql.append(" and apontamento_horas.id_conta_pagar = ");
			sql.append(ConsultaAH.getContasPagar().getId());
		}
		
		if(ConsultaAH.getSubAtividade() != null && ConsultaAH.getSubAtividade().getId() != null && ConsultaAH.getSubAtividade().getId() != 0) {
			sql.append(" and apontamento_horas.id_sub_atividade = ");
			sql.append(ConsultaAH.getSubAtividade().getId());
		}
		
		if(ConsultaAH.getSubAtividade() != null && ConsultaAH.getSubAtividade().getRecursoHumano() != null && ConsultaAH.getSubAtividade().getRecursoHumano().getId() != 0) {
			sql.append(" and sub_atividades.id_recurso_humano = ");
			sql.append(ConsultaAH.getSubAtividade().getRecursoHumano().getId());
		}
		
		if(ConsultaAH.getNrHoras() != null && ConsultaAH.getNrHoras() != 0) {
			sql.append(" and apontamento_horas.nr_horas = ");
			sql.append(ConsultaAH.getNrHoras());
		}
		
		if(ConsultaAH.getNrHorasExtras() != null && ConsultaAH.getNrHorasExtras() != 0) {
			sql.append(" and apontamento_horas.nr_horas_extras = ");
			sql.append(ConsultaAH.getNrHorasExtras());
		}
		
		if(ConsultaAH.getData() != null) {
			sql.append(" and apontamento_horas.data = ");
			sql.append(ConsultaAH.getData());
		}
		
		if(ConsultaAH.getDtDe() != null && ConsultaAH.getDtAte() != null) {
			sql.append(" and apontamento_horas.data between str_to_date('");
			sql.append(ConsultaAH.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(ConsultaAH.getDtAte());
			sql.append("','%Y-%m-%d')");
		}
		
		if(ConsultaAH.getStPago() != null) {
			sql.append(" and apontamento_horas.st_pago = ");
			sql.append(ConsultaAH.getStPago());
		}
		
		//System.out.println("Query apontamentos: " + sql.toString());
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				ApontamentoHoras ah = new ApontamentoHoras();
				
				ah.setId(rs.getInt("id"));
				
				Integer idSA = rs.getInt("id_sub_atividade");
				SubAtividade sa = new SubAtividade(idSA);
				ah.setSubAtividade(sa);
				
				Integer idCP = rs.getInt("id_conta_pagar");
				ContasPagar cp = new ContasPagar(idCP);
				ah.setContasPagar(cp);
				
				ah.setNrHoras(rs.getInt("nr_horas"));
				ah.setNrHorasExtras(rs.getInt("nr_horas_extras"));
				ah.setData(rs.getDate("data"));
				ah.setStAtivo(rs.getBoolean("st_pago"));
				ah.setStAtivo(rs.getBoolean("st_ativo"));
				
				listaApontamentos.add(ah);
				
			}
			
			return listaApontamentos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
