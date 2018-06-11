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
    /**
     * Adiciona um novo pagamento para o contas a receber de um pedido
     * @param pedido
     * @param valor 
     */
    private static void adicionarPagamento( PedidoVenda pedido, double valor){
        ContaReceber contaReceber = pedido.getContaReceber();
        List<Pagamentos> listPgamentos = contaReceber.getPagamentos();
        // vericar valor pago menor que falta
        double valorFalta = DoubleToStr.twoPlaces(contaReceber.getValorFalta());
        if ( DoubleToStr.twoPlaces(valor) > valorFalta ){
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
    
    /**
     * Adiciona um novo pagamento a um conta a receber. Caso o valor pago
     * seja igual ao valor pendente, o pedido será atualizado para o status Pago,
     * do contrário o pedido será atualizado para o status PagoParical
     * @param pedido
     * @param valor
     * @return 
     */
    public static String Pagar( PedidoVenda pedido, double valor ){
        double valorFalta = DoubleToStr.twoPlaces(
                pedido.getContaReceber().getValorFalta());
        
        adicionarPagamento(pedido, valor);
        
        // Atualizando o status do pedido de venda
        if ( DoubleToStr.isEqualTwoPlaces(valor, valorFalta) ){
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
    
    /**
     * Altera o status de um pedido para Cancelado e realiza o pagamento 
     * para o retorno do saldo do cliente.
     * @param pedido
     * @return 
     */
    public static String Cancelar( PedidoVenda pedido ){
        double valorFalta = DoubleToStr.twoPlaces(
                pedido.getContaReceber().getValorFalta());
        
        adicionarPagamento(pedido, valorFalta);
        
        // Atualizando o status do pedido de venda
        pedido.setStatus(StatusPedidoVenda.Cancelado);
        Repository.getInstance().add(pedido);
        Repository.getInstance().persistAll();
        return "Pedido pago parcial.";
        
        
    }
}
