/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Davi
 */
public class TabelaClass {
    
    public TabelaClass(){}
    public TabelaClass(String nome){
    this.nome = nome;
    this.totalVitorias = 0;
    }
    
                 
              
              private Integer pontos;
              private String nome;
              private Integer jogos;
              private Integer vitorias;
              private Integer derrotas;
              private Integer empates;
              private Integer golsFeitos;
              private Integer golsSofridos;
              private Integer saldoGols;
              private Integer ano;
              private Integer totalVitorias;

    public Integer getTotalVitorias() {
        return totalVitorias;
    }

    public void setTotalVitorias(Integer totalVitorias) {
        this.totalVitorias = totalVitorias;
    }
    
              

    public Integer getPontos() {
        return pontos;
    }

    public String getNome() {
        return nome;
    }

    public Integer getJogos() {
        return jogos;
    }

    public Integer getVitorias() {
        return vitorias;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public Integer getEmpates() {
        return empates;
    }

    public Integer getGolsFeitos() {
        return golsFeitos;
    }

    public Integer getGolsSofridos() {
        return golsSofridos;
    }

    public Integer getSaldoGols() {
        return saldoGols;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setJogos(Integer jogos) {
        this.jogos = jogos;
    }

    public void setVitorias(Integer vitorias) {
        this.vitorias = vitorias;
    }

    public void setDerrotas(Integer derrotas) {
        this.derrotas = derrotas;
    }

    public void setEmpates(Integer empates) {
        this.empates = empates;
    }

    public void setGolsFeitos(Integer golsFeitos) {
        this.golsFeitos = golsFeitos;
    }

    public void setGolsSofridos(Integer golsSofridos) {
        this.golsSofridos = golsSofridos;
    }

    public void setSaldoGols(Integer saldoGols) {
        this.saldoGols = saldoGols;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
    
}
