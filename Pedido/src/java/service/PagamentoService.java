/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.ContaReceber;
import domain.Pagamentos;
import domain.PedidoVenda;
import domain.StatusPedidoVenda;
import entities.Repository;

/**
 *
 * @author vitor
 */
public class PagamentoService {
    public static String Pagar( PedidoVenda pedido, double valor ){
        ContaReceber contaReceber = pedido.getContaReceber();
        // vericar valor pago menor que falta
        double valorFalta = contaReceber.getValor() - contaReceber.getValorPago();
        if ( valor > valorFalta ){
            throw new IllegalStateException("O valor pago ultrapassa o valor que falta!");
        }
        // Adicionando o pagamento ao contas a receber
        Pagamentos pagamento = new Pagamentos();
        pagamento.setValor(valor);
        contaReceber.getPagamentos().add(pagamento);
        // Atualizando o saldo do conta receber
        contaReceber.setValorPago(contaReceber.getValorPago() + valor);
        // Atualizando o saldo do cliente
        contaReceber.getCliente().getCredito().adicionaSaldo(-valor);
        
        // Persistindo
        Repository.getInstance().add(contaReceber);
        Repository.getInstance().add(contaReceber.getCliente());
        
        // Atualizando o status do pedido de venda
        if ( valor == valorFalta ){
            pedido.setStatus(StatusPedidoVenda.Pago);
            Repository.getInstance().add(pedido);
            Repository.getInstance().persistAll();;
            return "Pedido pago completo.";
        }
        
        pedido.setStatus(StatusPedidoVenda.PagoParcial);
        Repository.getInstance().add(pedido);
        Repository.getInstance().persistAll();;
        return "Pedido pago parcial.";
        
        
    }
}
