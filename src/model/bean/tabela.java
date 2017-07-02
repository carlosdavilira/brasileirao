/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Arrays;

/**
 *
 * @author betim
 */

public class tabela {
    
    int b_time[][] = new int[4][2];
    int  r_Pontos[][]=new int[4][8];
    String nomes_time[]= new String[4];
    
   
    public void resultados(int c,int f,int i) {
      
                b_time[i][0]=c;
                b_time[i][1]=f;
              
                            
    }
    public void setnomes(String s,int i) {
      
                nomes_time[i]=s;    
                
    }
    public String getnomes(int i) { 
              
                return nomes_time[i];    
       }           
    
    
    public int getTabela(int x , int j){
    
            return r_Pontos[x][j] ;
 
    }
    public void setTabela(int k , int o,int s){
            
             r_Pontos[k][o]= s;
 
    }
    
    
    
    public void setPontos(){
        for (int i = 0; i < 4; i++) {
          if(b_time[i][0]>b_time[i][1]) { 
          r_Pontos[i][0]=3;  }
            
          if(b_time[i][0]==b_time[i][1]){  
          r_Pontos[i][0]=1;  }
          if(b_time[i][0]==b_time[i][1]){  
          r_Pontos[i][0]=1;  }
    }  
    }
    
   
    public void setVitorias(){
        for (int i = 0; i < 4; i++) {
          if(b_time[i][0]>b_time[i][1]){ 
          r_Pontos[i][2]=1;  
        }else r_Pontos[i][2]=0;      
    }}
    public void setDerrotas(){
        for (int i = 0; i < 4; i++) {
          if(b_time[i][0]<b_time[i][1])  
          r_Pontos[i][3]=1;  
        }      
    }
    public void setEmpate(){
        for (int i = 0; i < 4; i++) {
          if(b_time[i][0]== b_time[i][1])  
          r_Pontos[i][4]=1;  
        }      
    }
    
    public void setGols_feitos(){
    for (int i = 0; i < 4; i++) {      
          r_Pontos[i][5]=b_time[i][0];  
        }   
    }
    public void setGols_sofridos(){
    for (int i = 0; i < 4; i++) {      
          r_Pontos[i][6]=b_time[i][1];  
        }   
    }
    public void setS_gols(){
    for (int i = 0; i < 4; i++) {      
          r_Pontos[i][7]=b_time[i][0]-b_time[i][1];  
        }   
    }
    
     public void setTabela(){
        tabela t = new tabela();
        t.setVitorias();
        t.setDerrotas();
        t.setEmpate();
        t.setGols_feitos();
        t.setGols_sofridos();
        t.setS_gols();
  
}
     
}


