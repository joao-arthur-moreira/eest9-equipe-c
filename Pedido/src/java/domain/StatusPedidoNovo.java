/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.Context;
import entities.Repository;
import service.LimiteCreditoClienteService;

/**
 *
 * @author vitor
 */
public class StatusPedidoNovo implements IStatusPedidoVenda {
    
    @Override
    public String cancelar(PedidoVenda pedido, Usuario usuario) {
          // Redireciona par a tela do cadastro de novo pedido
          return "go:domain.PedidoVenda@novoPedidoVenda";
    }

    @Override
    public String pagar(PedidoVenda pedido, double valor, Usuario usuario) {
        throw new IllegalStateException("O pedido ainda não foi gravado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String gravarNovo(PedidoVenda pedido) {
        // Verifica se existe algum item digitado 
        if ( pedido.getItens().isEmpty() ){
            throw new IllegalStateException("O pedido não contém nenhum item digitado.");
        }
        // Verificando se o cliente possuí saldo suficiente
        double credito = LimiteCreditoClienteService.consultaLimiteCreditoDisponivel(pedido.getCliente());
        if ( credito < pedido.getTotal() ){
            throw new IllegalStateException("O cliente não possui saldo suficiente para realizar o pedido.");
        }
        // Adicionando o contas a receber 
        ContaReceber receber = new ContaReceber();
        receber.setCliente(pedido.getCliente());
        receber.setValor(pedido.getTotal());
        receber.setValorPago(0);
        pedido.setContaReceber(receber);
        // Atualizando o saldo do cliente
        pedido.getCliente().getCredito().adicionaSaldo(pedido.getTotal()); 
        // Atualizando o status
        pedido.setStatus(StatusPedidoVenda.PendentePagamento);
        // Persistindo os dados
        Repository.getInstance().add(pedido);
        // Salvando o cliente
        Repository.getInstance().add(pedido.getCliente());
        // Salvando tudo
        Repository.getInstance().persistAll();
        // Redireciona para tela com o detalhe do pedido
        // Salva no contexto o id do pedido
        Context.setValue("ID_PEDIDO_VENDA",  pedido.getId());
        return "go:domain.PedidoVenda@detalhePedido";
    }
    
}
