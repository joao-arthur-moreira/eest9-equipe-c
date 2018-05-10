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
		// TODO Auto-generated method stub
	}

	@Override
	public void aprovar() {
		this.solicitacao.setStatus(new AguardandoRH(this.solicitacao));
		System.out.println(">>>>>>>> CHEFIA: Solicitação aprovada enviada para análise do RH <<<<<<<<<<<<<");
	}

	@Override
	public void recusar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void retornar(String motivo) {
		// TODO Auto-generated method stub

	}

}
