/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author vitor
 */
public class StatusPedidoCancelado implements IStatusPedidoVenda {

    @Override
    public String cancelar(PedidoVenda pedido, Usuario usuario) {
        throw new IllegalStateException("O pedido já econtra-se cancelado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String pagar(PedidoVenda pedido, double valor, Usuario usuario) {
        throw new IllegalStateException("O pedido encontra-se cancelado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String gravarNovo(PedidoVenda pedido) {
        throw new IllegalStateException("O pedido encontra-se cancelado."); //To change body of generated methods, choose Tools | Templates.
    }

}
