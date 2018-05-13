/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade.aula.extra;

import java.util.Arrays;

/**
 *
 * @author vitor
 */
public class SolicitacaoV2 extends Solicitacao {

    public String getStatusNome(char st) {
        switch (st) {
            case ST_ABERTA:
                return "Aberta";
            case ST_AGUARDANDO_CHEFIA:
                return "Aguardando chefia";
            case ST_AGUARDANDO_RH:
                return "Aguardando RH";
            case ST_APROVADA:
                return "Aprovada";
            case ST_NOVA_SOLICITACAO:
                return "Nova solicitação";
            case ST_RECUSADA:
                return "Recusada";
            default:
                return "Indefinido";

        }
    }

    private void valida(char[] statusPermitidos, char newStatus) throws Exception {
//        boolean contains = Arrays.asList(statusPermitidos).contains(status);
        boolean contains = new String(statusPermitidos).indexOf(status) != -1;
        if (!contains) {
            throw new Exception("Não é possível alterar status para "
                    + "\"" + getStatusNome(newStatus) + "\", enquanto solicitação com "
                    + "status \"" + getStatusNome(status) + "\".");
        }
    }

    @Override
    public void alterarStatusAguardandoAprovacaoChefe() throws Exception {
        char[] lista = new char[]{ST_NOVA_SOLICITACAO, ST_AGUARDANDO_RH};
        valida(lista, ST_AGUARDANDO_CHEFIA);
        status = ST_AGUARDANDO_CHEFIA;
    }

    @Override
    public void alterarStatusAguardandoAprovacaoChefe(String motivo) throws Exception {
        alterarStatusAguardandoAprovacaoChefe();
        motivoRetorno = motivo;
    }

    @Override
    public void alterarStatusNovaSolicitacao() throws Exception {
        char[] lista = new char[]{ST_ABERTA};
        valida(lista, ST_NOVA_SOLICITACAO);
        status = ST_NOVA_SOLICITACAO;
    }

    @Override
    public void alterarStatusAguardandoAprovacaoRh() throws Exception {
        char[] lista = new char[]{ST_AGUARDANDO_CHEFIA};
        valida(lista, ST_AGUARDANDO_RH);
        status = ST_AGUARDANDO_RH;
    }

    @Override
    public void alterarStatusAprovada() throws Exception {
        char[] lista = new char[]{ST_AGUARDANDO_RH};
        valida(lista, ST_APROVADA);
        status = ST_APROVADA;
    }

    @Override
    public void alterarStatusRecusada() throws Exception {
        char[] lista = new char[]{ST_AGUARDANDO_RH, ST_AGUARDANDO_CHEFIA};
        valida(lista, ST_RECUSADA);
        status = ST_RECUSADA;
    }

}
