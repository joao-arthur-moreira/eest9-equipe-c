/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividae.sala;

/**
 *
 * @author vitor
 */
public class AtividaeSala {

    /**
     * @param args the command lhb b   ASSAAAine arguments
     */
    public static void main(String[] args) {
        int atual = 0;
        int anterior = 1;
        // TODO code application logic here
        while ( true ){
            if ( atual == 0 ){
                System.out.println(atual);
            }
            atual = atual + anterior;
            anterior = atual - anterior;
            if ( atual > 100 ){
                break;
            }
            System.out.println(atual);
        }
           
    }

//public static class Fibonacci{
// static long Fibo(int n){
//     if(n <2){
//     return n;
//     }else{
//         return Fibo(n -1) + Fibo(n-2);
//     }
//}



}
