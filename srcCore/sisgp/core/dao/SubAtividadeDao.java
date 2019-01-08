package sisgp.core.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.Atividade;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusAtividade;
import sisgp.dominio.StatusSistemaAtividade;
import sisgp.dominio.SubAtividade;

public class SubAtividadeDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		SubAtividade sat = (SubAtividade) entidade;
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into sub_atividades (id_atividade,codigo,nome,descricao,");
		sql.append("id_recurso_humano,nr_horas_previsto,id_status_atividade,id_st_sist_atividade,");
		sql.append("dt_inicio,dt_fim,st_ativo) values (?,?,?,?,?,?,?,?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			sat.setStAtivo(true);
			sat.setStSistAtividade(new StatusSistemaAtividade(1));//atividade não iniciada
			
			pst.setInt(1, sat.getAtividade().getId());
			pst.setString(2, sat.getCodigo());
			pst.setString(3, sat.getNome());		
			pst.setString(4, sat.getDescricao());
			pst.setInt(5, sat.getRecursoHumano().getId());
			pst.setInt(6, sat.getNrHorasPrevisto());
			pst.setInt(7, sat.getStatusAtividade().getId());
			pst.setInt(8, sat.getStSistAtividade().getId());
			pst.setDate(9,(Date) sat.getDtInicio());
			pst.setDate(10, (Date) sat.getDtFim());
			pst.setBoolean(11, sat.isStAtivo());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				sat.setId(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		SubAtividade sat = (SubAtividade) entidade;
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update sub_atividades set id_atividade = ?,codigo = ?,nome = ?,descricao = ?,");
		sql.append("id_recurso_humano = ?,nr_horas_previsto = ?,id_status_atividade = ?,id_st_sist_atividade = ?,");
		sql.append("dt_inicio = ?,dt_fim = ?,st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			sat.setStAtivo(true);
			
			pst.setInt(1, sat.getAtividade().getId());
			pst.setString(2, sat.getCodigo());
			pst.setString(3, sat.getNome());		
			pst.setString(4, sat.getDescricao());
			pst.setInt(5, sat.getRecursoHumano().getId());
			pst.setInt(6, sat.getNrHorasPrevisto());
			pst.setInt(7, sat.getStatusAtividade().getId());
			pst.setInt(8, sat.getStSistAtividade().getId());
			
			System.out.println(sat.getDtInicio().getClass().getName());
			if(sat.getDtInicio().getClass().getName().equals("java.sql.Date")) {
				pst.setDate(9,(Date) sat.getDtInicio());
				pst.setDate(10, (Date) sat.getDtFim());
			}else {
				pst.setTimestamp(9, (Timestamp) sat.getDtInicio());
				pst.setTimestamp(10, (Timestamp) sat.getDtFim());
			}
						
			pst.setBoolean(11, sat.isStAtivo());
			
			pst.setInt(12, sat.getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		SubAtividade sat = (SubAtividade) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update sub_atividades set ");
		sql.append("st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			sat.setStAtivo(false);
			
			pst.setBoolean(1, sat.isStAtivo());
			
			pst.setInt(2, sat.getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaSubAtividade = new ArrayList<EntidadeDominio>();
		
		SubAtividade saConsulta = (SubAtividade) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sub_atividades.*,status_atividade.status as st_at ,status_sistema_atividade.status as st_sis_at ");
		sql.append(" from sub_atividades inner join status_atividade ");
		sql.append(" on sub_atividades.id_status_atividade = status_atividade.id ");
		sql.append(" inner join status_sistema_atividade ");
		sql.append(" on sub_atividades.id_st_sist_atividade = status_sistema_atividade.id ");
		sql.append(" where sub_atividades.st_ativo = 1 ");

		if(saConsulta.getId() != null && saConsulta.getId() != 0) {
			sql.append(" and sub_atividades.id = ");
			sql.append(saConsulta.getId());
		}
		
		if(saConsulta.getRecursoHumano() != null) {
			sql.append(" and sub_atividades.id_recurso_humano = ");
			sql.append(saConsulta.getRecursoHumano().getId());
		}
		
		if(saConsulta.getAtividade() != null) {
			sql.append(" and sub_atividades.id_atividade = ");
			sql.append(saConsulta.getAtividade().getId());
		}
		
		if(saConsulta.getCodigo() != null && !saConsulta.getCodigo().trim().equals("")) {
			sql.append(" and sub_atividades.codigo like '%");
			sql.append(saConsulta.getCodigo());
			sql.append("%'");
		}
		
		if(saConsulta.getNome() != null && !saConsulta.getNome().trim().equals("")) {
			sql.append(" and sub_atividades.nome like '%");
			sql.append(saConsulta.getNome());
			sql.append("%'");
		}
		
		if(saConsulta.getDescricao() != null && !saConsulta.getDescricao().trim().equals("")) {
			sql.append(" and sub_atividades.descricao like '%");
			sql.append(saConsulta.getDescricao());
			sql.append("%'");
		}
		
		if(saConsulta.getNrHorasPrevisto() != null && saConsulta.getNrHorasPrevisto() != 0) {
			sql.append(" and nr_horas_previsto = ");
			sql.append(saConsulta.getNrHorasPrevisto());
		}
		
		if(saConsulta.getStatusAtividade() != null) {
			sql.append(" and sub_atividades.id_status_atividade = ");
			sql.append(saConsulta.getStatusAtividade().getId());
		}
		
		if(saConsulta.getStSistAtividade() != null) {
			sql.append(" and sub_atividades.id_st_sist_atividade = ");
			sql.append(saConsulta.getStSistAtividade().getId());
		}
		
		
		if(saConsulta.getDtInicio() != null) {
			sql.append(" and sub_atividades.dt_inicio = str_to_date('");
			sql.append(saConsulta.getDtInicio());
			sql.append("','%Y-%m-%d')");
		}
		
		if(saConsulta.getDtFim() != null) {
			sql.append(" and sub_atividades.dt_fim = str_to_date('");
			sql.append(saConsulta.getDtFim());
			sql.append("','%Y-%m-%d')");
		}
		
		sql.append(" order by sub_atividades.id ");
		
		//System.out.println("Sub_Atividade:" + sql.toString());
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				SubAtividade sat = new SubAtividade();
				
				sat.setId(rs.getInt("id"));
				
				Integer idAtividade = rs.getInt("id_atividade");
				Atividade at = new Atividade(idAtividade);
				sat.setAtividade(at);
				
				sat.setCodigo(rs.getString("codigo"));
				sat.setNome(rs.getString("nome"));
				sat.setDescricao(rs.getString("descricao"));
				sat.setNrHorasPrevisto(rs.getInt("nr_horas_previsto"));
				
				Integer idStatus = rs.getInt("id_status_atividade");
				String stAt = rs.getString("st_at");
				StatusAtividade sa = new StatusAtividade(idStatus,stAt);
				sat.setStatusAtividade(sa);
				
				Integer idStSist = rs.getInt("id_st_sist_atividade");
				String stSisAt = rs.getString("st_sis_at");
				StatusSistemaAtividade ssa = new StatusSistemaAtividade(idStSist,stSisAt);
				sat.setStSistAtividade(ssa);
				
				Integer idRH = rs.getInt("id_recurso_humano");
				RecursoHumano rh = new RecursoHumano(idRH);
				sat.setRecursoHumano(rh);
				
				sat.setDtInicio(rs.getTimestamp("dt_inicio"));
				sat.setDtFim(rs.getTimestamp("dt_fim"));
				sat.setStAtivo(rs.getBoolean("st_ativo"));
				
				ApontamentoHoras ah = new ApontamentoHoras();
				ApontamentoHorasDao ahd = new ApontamentoHorasDao();
				
				ah.setSubAtividade(sat);
				
				List<EntidadeDominio> listAh = ahd.consultar(ah);					
				
				List<ApontamentoHoras> listaApontamentoHoras = (List<ApontamentoHoras>)(List<?>)listAh;
				sat.setListaApontamentoHoras(listaApontamentoHoras);
				
				listaSubAtividade.add(sat);
			}
			
			return listaSubAtividade;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
