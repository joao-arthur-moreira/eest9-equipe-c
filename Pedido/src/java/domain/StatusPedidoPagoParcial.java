/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import service.PagamentoService;

public class StatusPedidoPagoParcial implements IStatusPedidoVenda {

    @Override
    public String cancelar(PedidoVenda pedido, Usuario usuario) {
        PagamentoService.Cancelar(pedido, usuario);
        return "Pedido cancelado com êxito.";
    }

    @Override
    public String pagar(PedidoVenda pedido, double valor, Usuario usuario) {
        return PagamentoService.Pagar(pedido, valor, usuario);
    }

    @Override
    public String gravarNovo(PedidoVenda pedido) {
        throw new IllegalStateException("O pedido de venda já foi gravado anteriormente."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
