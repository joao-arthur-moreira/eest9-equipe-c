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
public interface IStatusPedidoVenda {
    public String cancelar(PedidoVenda pedido);
    public String pagar(PedidoVenda pedido);
    public String gravarNovo(PedidoVenda pedido);
}
