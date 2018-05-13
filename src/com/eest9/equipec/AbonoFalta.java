package com.eest9.equipec;

import java.util.Date;

import com.eest9.equipec.model.Funcionario;
import com.eest9.equipec.model.Solicitacao;
import com.eest9.equipec.model.Tipo;

public class AbonoFalta {
	
	public static void main(String[] args) {
		
		Funcionario funcionarioSolicitante = new Funcionario();
		funcionarioSolicitante.setId(1L);
		funcionarioSolicitante.setNome("João Arthur");
		funcionarioSolicitante.setTipo(Tipo.NORMAL);
		
		Solicitacao solicitacaoAbono = new Solicitacao();
		solicitacaoAbono.setId(1L);
		solicitacaoAbono.setFuncionario(funcionarioSolicitante);
		solicitacaoAbono.setInicio(new Date());
		solicitacaoAbono.setFim(solicitacaoAbono.getInicio());
		solicitacaoAbono.setMotivo("Teste");
		
		solicitacaoAbono.solicitar();	
		solicitacaoAbono.aprovar();
		solicitacaoAbono.recusar();
		solicitacaoAbono.aprovar();
		solicitacaoAbono.aprovar();
		
	}

}
