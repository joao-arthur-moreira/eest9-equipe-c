/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.Repository;

/**
 *
 * @author vitor
 */
public class StatusPedidoNovo implements IStatusPedidoVenda {
    
    @Override
    public String cancelar(PedidoVenda pedido) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String pagar(PedidoVenda pedido, double valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String gravarNovo(PedidoVenda pedido) {
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
        return "Pedido salvo com êxito!";
    }
    
}
