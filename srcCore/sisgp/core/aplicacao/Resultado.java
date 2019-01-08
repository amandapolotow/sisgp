package sisgp.core.aplicacao;

import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public class Resultado extends EntidadeAplicacao{
	
	private List<EntidadeDominio> listaEntidades;
	private String mensagem;
	
	public List<EntidadeDominio> getListaEntidade(){
		return listaEntidades;
	}
	
	public void setListaEntidade(List<EntidadeDominio> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
