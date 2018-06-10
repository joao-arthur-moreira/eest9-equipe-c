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
public enum EnumEstados {
    AC("AC"),
    AL("AL"),
    AM("AM"),
    AP("AP"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MG("MG"),
    MS("MS"),
    MT("MT"),
    PA("PA"),
    PB("PB"),
    PE("PE"),
    PI("PI"),
    PR("PR"),
    RJ("RJ"),
    RN("RN"),
    RO("RO"),
    RR("RR"),
    RS("RS"),
    SC("SC"),
    SE("SE"),
    SP("SP"),
    TO("TO");
    private final String estado;

    EnumEstados(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.estado; //To change body of generated methods, choose Tools | Templates.
    }

}
