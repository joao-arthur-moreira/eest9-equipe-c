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
import java.util.List;

/**
 *
 * @author vitor
 */
public class PagamentoService {
    private static void adicionarPagamento( PedidoVenda pedido, double valor){
        ContaReceber contaReceber = pedido.getContaReceber();
        List<Pagamentos> listPgamentos = contaReceber.getPagamentos();
        // vericar valor pago menor que falta
        double valorFalta = Double.parseDouble(String.format("%.2f",contaReceber.getValorFalta()).replace(",", ".") );
        if ( valor > valorFalta ){
            throw new IllegalStateException("O valor pago ultrapassa o valor que falta!");
        }
        // Adicionando o pagamento ao contas a receber
        Pagamentos pagamento = new Pagamentos();
        pagamento.setValor(valor);
        listPgamentos.add(pagamento);
        // Atualizando o saldo do conta receber
        contaReceber.setValorPago(contaReceber.getValorPago() + valor);
        // Atualizando o saldo do cliente
        contaReceber.getCliente().getCredito().adicionaSaldo(-valor);
        
        // Persistindo
        Repository.getInstance().add(contaReceber);
        Repository.getInstance().add(contaReceber.getCliente());
    }
    
    public static String Pagar( PedidoVenda pedido, double valor ){
        double valorFalta = pedido.getContaReceber().getValorFalta();
        
        adicionarPagamento(pedido, valor);
        
        // Atualizando o status do pedido de venda
        if ( String.format("%.2f",valor).equals(String.format("%.2f",valorFalta)) ){
            pedido.setStatus(StatusPedidoVenda.Pago);
            Repository.getInstance().add(pedido);
            Repository.getInstance().persistAll();
            return "Pedido pago completo.";
        }
        
        pedido.setStatus(StatusPedidoVenda.PagoParcial);
        Repository.getInstance().add(pedido);
        Repository.getInstance().persistAll();
        return "Pedido pago parcial.";
        
        
    }
    
    public static String Cancelar( PedidoVenda pedido ){
        double valorFalta = pedido.getContaReceber().getValorFalta();
        
        adicionarPagamento(pedido, valorFalta);
        
        // Atualizando o status do pedido de venda
        pedido.setStatus(StatusPedidoVenda.Cancelado);
        Repository.getInstance().add(pedido);
        Repository.getInstance().persistAll();
        return "Pedido pago parcial.";
        
        
    }
}
