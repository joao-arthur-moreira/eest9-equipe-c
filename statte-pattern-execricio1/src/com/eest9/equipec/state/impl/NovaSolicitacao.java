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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

	}

	@Override
	public void recusar() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

	}

	@Override
	public void retornar(String motivo) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

	}

}
