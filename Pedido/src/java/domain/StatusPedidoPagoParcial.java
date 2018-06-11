/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import service.PagamentoService;

public class StatusPedidoPagoParcial implements IStatusPedidoVenda {

    @Override
    public String cancelar(PedidoVenda pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String pagar(PedidoVenda pedido, double valor) {
        return PagamentoService.Pagar(pedido, valor);
    }

    @Override
    public String gravarNovo(PedidoVenda pedido) {
        throw new IllegalStateException("O pedido de venda já foi gravado anteriormente."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
