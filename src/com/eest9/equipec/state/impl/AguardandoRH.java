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
		// TODO Auto-generated method stub

	}

	@Override
	public void aprovar() {
		this.solicitacao.setStatus(null);
		System.out.println(">>>>>>>> RH: Solicitação aprovada pelo RH <<<<<<<<<<<<<");
	}

	@Override
	public void recusar() {
		this.solicitacao.setStatus(new AguardandoChefia(solicitacao));
		System.out.println(">>>>>>>> RH: Solicitação recusada retornada para chefia <<<<<<<<<<<<<");
	}

	@Override
	public void retornar(String motivo) {
		// TODO Auto-generated method stub

	}

}
