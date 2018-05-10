package com.eest9.equipec.state.impl;

import com.eest9.equipec.model.Solicitacao;
import com.eest9.equipec.state.Status;

public class NovaSolicitacao implements Status {
	
	private Solicitacao solicitacao;
	
	public NovaSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	@Override
	public void solicitar() {
		this.solicitacao.setStatus(new AguardandoChefia(solicitacao));
		System.out.println(">>>>>>>> Solicitação enviada para chefia <<<<<<<<<<<<<");
	}

	@Override
	public void aprovar() {
		// TODO Auto-generated method stub

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
