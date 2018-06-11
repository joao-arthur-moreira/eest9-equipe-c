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
public class StatusPedidoPago implements IStatusPedidoVenda {

    @Override
    public String cancelar(PedidoVenda pedido) {
        // Como o pedido já econtra-se pago é preciso apenas 
        // alterar o status para cancelado
        pedido.setStatus(StatusPedidoVenda.Cancelado);
        Repository.save(pedido);
        return "Pedido cancelado com êxito.";
    }

    @Override
    public String pagar(PedidoVenda pedido, double valor) {
        throw new IllegalStateException("O pedido de venda já encontra-se pago."); 
    }

    @Override
    public String gravarNovo(PedidoVenda pedido) {
        throw new IllegalStateException("O pedido de venda já foi gravado."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
