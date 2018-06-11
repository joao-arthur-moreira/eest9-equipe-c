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
public enum StatusPedidoVenda implements IStatusPedidoVenda {
    Cancelado(new StatusPedidoCancelado()),
    Novo(new StatusPedidoNovo()),
    Pago(new StatusPedidoPago()),
    PagoParcial(new StatusPedidoPagoParcial()),
    PendentePagamento(new StatusPedidoPendentePagamento());

    private final IStatusPedidoVenda status;

    StatusPedidoVenda(IStatusPedidoVenda status) {
        this.status = status;
    }

    @Override
    public String cancelar(PedidoVenda pedido, Usuario usuario) {
        return status.cancelar(pedido,usuario);
    }

    @Override
    public String pagar(PedidoVenda pedido, double valor, Usuario usuario) {
        return status.pagar(pedido, valor,usuario);
    }

    @Override
    public String gravarNovo(PedidoVenda pedido) {
        return status.gravarNovo(pedido);
    }

    @Override
    public String toString() {
        if ( status instanceof StatusPedidoPagoParcial){
            return "Pago parcial";
        }
        if ( status instanceof StatusPedidoPendentePagamento){
            return "Pendente de pagamento";
        }
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
