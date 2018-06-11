/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author vitor
 */
public class DoubleToStr {
    /**
    * Arredonda um double em duas casas decimais
    * @param vlr
    * @return 
    */
    static public double twoPlaces(double vlr){
        String str = String.format("%.2f", vlr).replace(",", ".");
        double ret = Double.parseDouble(str );
        return ret;
    }
    
    /**
     * Arredonda um double em três casas decimais
     * @param vlr
     * @return 
     */
    static public double threePlaces(double vlr){
        String str = String.format("%.3f", vlr).replace(",", ".");
        double ret = Double.parseDouble(str );
        return ret;
    }
    
    /**
     * Compara dois double arredondando para duas casas decimais
     * @param vlr1
     * @param vlr2
     * @return 
     */
    static public boolean isEqualTwoPlaces(double vlr1, double vlr2 ){
        return String.format("%.2f",vlr1).equals(String.format("%.2f",vlr2)) ;
    }
}
