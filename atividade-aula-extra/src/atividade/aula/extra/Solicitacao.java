/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade.aula.extra;

/**
 *
 * @author vitor
 */
public class Solicitacao {
    protected final char ST_ABERTA = 'A';
    protected final char ST_AGUARDANDO_CHEFIA = 'B';
    protected final char ST_AGUARDANDO_RH = 'C';
    protected final char ST_APROVADA = 'D';
    protected final char ST_NOVA_SOLICITACAO = 'E';
    protected final char ST_RECUSADA = 'F';
    
    protected char status = ST_ABERTA ;
    protected String motivoRetorno;
    /* 
        .... ADICIONAR NOVAS PROPRIEDADES ABAIXO
        .. COMO TAREFA SOLICITOU APENAS OS MÉTODOS ESTOU SUPRIMINDO
    */

    public String getMotivoRetorno() {
        return motivoRetorno;
    }
   

    public char getStatus() {
        return status;
    }
    
    
    public void alterarStatusNovaSolicitacao() throws Exception{
        char st = this.status;
        switch ( st ){
            case ST_AGUARDANDO_CHEFIA : 
                throw new Exception("Não é possível alterar status para "
                        + "\"Nova solicitação\", enquanto solicitação com "
                        + "status \"Aguardando chefia\". ");
            case ST_AGUARDANDO_RH:
                throw new Exception("Não é possível alterar status para "
                        + "\"Nova solicitação\", enquanto solicitação com "
                        + "status \"Aguardando RH\". ");
            case ST_APROVADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Nova solicitação\", enquanto solicitação com "
                        + "status \"Aprovada\". ");
            case ST_NOVA_SOLICITACAO: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Nova solicitação\", enquanto solicitação com "
                        + "status \"Nova solicitação\". ");
            case ST_RECUSADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Nova solicitação\", enquanto solicitação com "
                        + "status \"Reusada\". ");
            default:
                this.status = ST_NOVA_SOLICITACAO;
                
        }
    }
    
    /**
     *
     * @param motivo
     * @throws java.lang.Exception
     */
    public void alterarStatusAguardandoAprovacaoChefe(String motivo) throws Exception{
        // Altera o status
        this.alterarStatusAguardandoAprovacaoChefe();
        // Altera o motivo
        motivoRetorno = motivo;
        
    }
    
    public void alterarStatusAguardandoAprovacaoChefe() throws Exception{
        char st = this.status;
        switch ( st ){
            case ST_AGUARDANDO_CHEFIA : 
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando chefia\", enquanto solicitação com "
                        + "status \"Aguardando chefia\". ");
            case ST_AGUARDANDO_RH:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando chefia\", enquanto solicitação com "
                        + "status \"Aguardando RH\". ");
            case ST_APROVADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando chefia\", enquanto solicitação com "
                        + "status \"Aprovada\". ");
//            case ST_NOVA_SOLICITACAO: 
//                throw new Exception("Não é possível alterar status para "
//                        + "\"Aguardando chefia\", enquanto solicitação com "
//                        + "status \"Nova solicitação\". ");
            case ST_RECUSADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando chefia\", enquanto solicitação com "
                        + "status \"Reusada\". ");
            case ST_ABERTA: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando chefia\", enquanto solicitação com "
                        + "status \"Aberta\". ");
            default:
                this.status = ST_NOVA_SOLICITACAO;
                
        }
    }
    
    public void alterarStatusAguardandoAprovacaoRh() throws Exception{
        char st = this.status;
        switch ( st ){
//            case ST_AGUARDANDO_CHEFIA : 
//                throw new Exception("Não é possível alterar status para "
//                        + "\"Aguardando chefia\", enquanto solicitação com "
//                        + "status \"Aguardando chefia\". ");
            case ST_AGUARDANDO_RH:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando RH\", enquanto solicitação com "
                        + "status \"Aguardando RH\". ");
            case ST_APROVADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando RH\", enquanto solicitação com "
                        + "status \"Aprovada\". ");
            case ST_NOVA_SOLICITACAO: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando RH\", enquanto solicitação com "
                        + "status \"Nova solicitação\". ");
            case ST_RECUSADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando RH\", enquanto solicitação com "
                        + "status \"Reusada\". ");
            case ST_ABERTA: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Aguardando RH\", enquanto solicitação com "
                        + "status \"Aberta\". ");
            default:
                this.status = ST_AGUARDANDO_CHEFIA;
                
        }
    }
    
    public void alterarStatusRecusada() throws Exception{
         char st = this.status;
        switch ( st ){
//            case ST_AGUARDANDO_CHEFIA : 
//                throw new Exception("Não é possível alterar status para "
//                        + "\"Aguardando chefia\", enquanto solicitação com "
//                        + "status \"Aguardando chefia\". ");
//            case ST_AGUARDANDO_RH:
//                throw new Exception("Não é possível alterar status para "
//                        + "\"Aguardando RH\", enquanto solicitação com "
//                        + "status \"Aguardando RH\". ");
            case ST_APROVADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Recusada\", enquanto solicitação com "
                        + "status \"Aprovada\". ");
            case ST_NOVA_SOLICITACAO: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Recusada\", enquanto solicitação com "
                        + "status \"Nova solicitação\". ");
            case ST_RECUSADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Recusada\", enquanto solicitação com "
                        + "status \"Reusada\". ");
            case ST_ABERTA: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Recusada\", enquanto solicitação com "
                        + "status \"Aberta\". ");
            default:
                this.status = ST_RECUSADA;
                
        }
    }
    
    public void alterarStatusAprovada() throws Exception{
                 char st = this.status;
        switch ( st ){
//            case ST_AGUARDANDO_CHEFIA : 
//                throw new Exception("Não é possível alterar status para "
//                        + "\"Aguardando chefia\", enquanto solicitação com "
//                        + "status \"Aguardando chefia\". ");
//            case ST_AGUARDANDO_RH:
//                throw new Exception("Não é possível alterar status para "
//                        + "\"Aguardando RH\", enquanto solicitação com "
//                        + "status \"Aguardando RH\". ");
            case ST_APROVADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aprovada\", enquanto solicitação com "
                        + "status \"Aprovada\". ");
            case ST_NOVA_SOLICITACAO: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Aprovada\", enquanto solicitação com "
                        + "status \"Nova solicitação\". ");
            case ST_RECUSADA:
                throw new Exception("Não é possível alterar status para "
                        + "\"Aprovada\", enquanto solicitação com "
                        + "status \"Reusada\". ");
            case ST_ABERTA: 
                throw new Exception("Não é possível alterar status para "
                        + "\"Aprovada\", enquanto solicitação com "
                        + "status \"Aberta\". ");
            default:
                this.status = ST_APROVADA;
                
        }
    }
}
