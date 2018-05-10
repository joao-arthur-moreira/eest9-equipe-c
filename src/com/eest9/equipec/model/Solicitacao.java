package com.eest9.equipec.model;

import java.util.Date;

import com.eest9.equipec.state.Status;
import com.eest9.equipec.state.impl.NovaSolicitacao;


public class Solicitacao {
	
	private Long id;
	private Funcionario funcionario;
	private Date inicio;
	private Date fim;
	private String motivo;
	private String observacao;
	private Status status = new NovaSolicitacao();
	
	public void solicitar() {
		this.status.solicitar();
	}
	
	public void aprovar() {
		this.status.aprovar();
	}
	
	public void recusar() {
		this.status.recusar();
	}
	
	public void retornar(String motivo) {
		this.status.retornar(motivo);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

}
