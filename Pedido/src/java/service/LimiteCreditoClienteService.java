/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Cliente;
import entities.Repository;

/**
 *
 * @author vitor
 */
public class LimiteCreditoClienteService {
    /**
     * Executa uma consulta no banco e traz o valor atual do limite disponível
     * de um cliente
     * @param cliente
     * @return 
     */
    public static double consultaLimiteCreditoDisponivel(Cliente cliente){
        
        if (  cliente.getId() == null ){
             throw new IllegalStateException("Cliente inválido!");
        }
        
        String sql = "Select c.credito.limite - c.credito.utilizado "
                + " From Cliente c "
                + " Where c.id = :id";
        Double limite = Repository.queryUnique(sql, cliente.getId());
        return DoubleToStr.twoPlaces(limite);
        
    }
    
    /**
     * Atualiza o saldo do limeite de crédito de um cliente adicionando um valor
     * (Para subtrair o saldo utilize valor negativo)
     * @param cliente
     * @param valor 
     */
    public static void atualizaLimiteCreditoCliente(Cliente cliente, double valor){
        if (  cliente.getId() == null ){
             throw new IllegalStateException("Cliente inválido!");
        }
        
        
        String sql = "Update Cliente c set c.credito.utilizado = c.credito.utilizado + :valor "
                + " Where c.id = :id";
        
        Repository.executeUpdate(sql, DoubleToStr.twoPlaces(valor),cliente.getId());
    }
}
