package com.eest9.equipec.state.impl;

import com.eest9.equipec.model.Solicitacao;
import com.eest9.equipec.state.Status;

public class AguardandoRH implements Status {

	private Solicitacao solicitacao;
	
	public AguardandoRH(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	@Override
	public void solicitar() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

	}

	@Override
	public void aprovar() {
		this.solicitacao.setStatus(new Aprovada());
		System.out.println(">>>>>>>> RH: Solicitação aprovada pelo RH <<<<<<<<<<<<<");
	}

	@Override
	public void recusar() {
		this.solicitacao.setStatus( new Recusada());
		System.out.println(">>>>>>>> RH: Solicitação recusada retornada para chefia <<<<<<<<<<<<<");
	}

	@Override
	public void retornar(String motivo) {
                // Ajusta o motivo do retorno
                this.solicitacao.setObservacao(motivo);
                this.solicitacao.setStatus( new AguardandoChefia(this.solicitacao));
		System.out.println(">>>>>>>> RH: Solicitação retornada para chefia <<<<<<<<<<<<<");

	}

}
