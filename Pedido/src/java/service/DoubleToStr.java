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
    static public double twoPlaces(double vlr){
        String str = String.format("%.2f", vlr).replace(",", ".");
        double ret = Double.parseDouble(str );
        return ret;
    }
    
    static public double threePlaces(double vlr){
        String str = String.format("%.3f", vlr).replace(",", ".");
        double ret = Double.parseDouble(str );
        return ret;
    }
    
    static public boolean isEqualTwoPlaces(double vlr1, double vlr2 ){
        return String.format("%.2f",vlr1).equals(String.format("%.2f",vlr2)) ;
    }
}
