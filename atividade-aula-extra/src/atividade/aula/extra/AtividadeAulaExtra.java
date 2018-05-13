/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade.aula.extra;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitor
 */
public class AtividadeAulaExtra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Solicitacao solicitacao = new Solicitacao();
//        try {
//            // Primeiro teste alterar solicitação
//            solicitacao.alteraStatusAguardandoAprovacaoChefe();
//        } catch (Exception ex) {
//            Logger.getLogger(AtividadeAulaExtra.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        SolicitacaoV2 solicitacao = new SolicitacaoV2();
        try {
            // Primeiro teste alterar solicitação
            solicitacao.alterarStatusNovaSolicitacao();
            solicitacao.alterarStatusAguardandoAprovacaoChefe();
            solicitacao.alterarStatusAguardandoAprovacaoRh();
//            solicitacao.alterarStatusNovaSolicitacao();
            System.out.println("status= " +solicitacao.getStatusNome(solicitacao.status));
            
        } catch (Exception ex) {
            System.out.println("status= " +solicitacao.getStatusNome(solicitacao.status));
            Logger.getLogger(AtividadeAulaExtra.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
}
