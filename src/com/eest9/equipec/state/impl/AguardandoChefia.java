package com.eest9.equipec.state.impl;

import com.eest9.equipec.model.Solicitacao;
import com.eest9.equipec.state.Status;

public class AguardandoChefia implements Status {

	private Solicitacao solicitacao;

	public AguardandoChefia(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	@Override
	public void solicitar() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void aprovar() {
		this.solicitacao.setStatus(new AguardandoRH(this.solicitacao));
		System.out.println(">>>>>>>> CHEFIA: Solicitação aprovada enviada para anälise do RH <<<<<<<<<<<<<");
	}

	@Override
	public void recusar() {
               this.solicitacao.setStatus(new Recusada());
               System.out.println(">>>>>>>> CHEFIA: Solicitação recusada <<<<<<<<<<<<<");

	}

	@Override
	public void retornar(String motivo) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

	}

}
