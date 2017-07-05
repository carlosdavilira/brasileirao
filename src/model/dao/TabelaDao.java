/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.TabelaClass;
import model.bean.tabela;

/**
 *
 * @author betim
 */
public class TabelaDao {
    public int campeonatoid=0;
    public int idtime[]=new int[4];
 
    
    /**
     *
     * @param t
     */
    public void create(tabela t, int a){
        
        
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        
        try {   
            ResultSet rs222 = null;
            stmt = con.prepareStatement("SELECT * FROM tabela,campeonatos where (tabela.campeonatoid = campeonatos.campeonatoid) and tabela.time = ? and campeonatos.ano=?");
             
            for (int i = 0; i < 4; i++) {  
            stmt.setString(1, t.getnomes(i));
            stmt.setInt(2, a); 
             rs222 = stmt.executeQuery();
//             for (int j = 0; j < 4; j++) {
           if(rs222.next()){
             int idtime1 = rs222.getInt("idtime"); 
             this.idtime[i]= idtime1;
              System.out.println(Arrays.toString(this.idtime));
                 }    }
            
            ResultSet rs22 = null;
            stmt = con.prepareStatement("SELECT * FROM tabela,campeonatos where (tabela.campeonatoid = campeonatos.campeonatoid) and tabela.time = ? and campeonatos.ano=?");
            stmt.setString(1, t.getnomes(1));
            stmt.setInt(2, a);
            rs22 = stmt.executeQuery();
            if(rs22.next()){
 
                System.out.println("Executei if(rs.next())");
            
                stmt = con.prepareStatement("UPDATE tabela set pontos = pontos + ? ,vitorias= vitorias + ? ,derrotas= derrotas + ?,empates= empates + ?"
                    + " ,gols_feitos = gols_feitos + ?, gols_sofridos = gols_sofridos + ?, saldo_gols = saldo_gols + ?, jogos = jogos +1, campeonatoid = ? WHERE time = ? and idtime = ? ");
            for (int i = 0; i < 4; i++) {
       
            stmt.setInt(1, t.getTabela(i, 0));
            stmt.setInt(2, t.getTabela(i, 2));
            stmt.setInt(3, t.getTabela(i, 3));
            stmt.setInt(4, t.getTabela(i, 4));
            stmt.setInt(5, t.getTabela(i, 5));
            stmt.setInt(6, t.getTabela(i, 6));
            stmt.setInt(7, t.getTabela(i, 7));
            stmt.setInt(8,campeonatoid);
            stmt.setString(9, t.getnomes(i));
            stmt.setInt(10, idtime[i]);
            stmt.executeUpdate();
            }
               
                
            }else{
            System.out.println("Executei else");
            Connection con1 = ConnectionFactory.getConnection(); 
            PreparedStatement stmt1 = null;
                stmt1 = con.prepareStatement("INSERT INTO tabela(time,pontos,vitorias,derrotas,empates,gols_feitos,gols_sofridos" 
                + ",saldo_gols,jogos,campeonatoid) VALUES(?,?,?,?,?,?,?,?,1,?)");
                for (int i = 0; i < 4; i++) {
                    
                stmt1.setString(1, t.getnomes(i));    
                stmt1.setInt(2, t.getTabela(i, 0));
                stmt1.setInt(3, t.getTabela(i, 2));
                stmt1.setInt(4, t.getTabela(i, 3));
                stmt1.setInt(5, t.getTabela(i, 4));
                stmt1.setInt(6, t.getTabela(i, 5));
                stmt1.setInt(7, t.getTabela(i, 6));
                stmt1.setInt(8, t.getTabela(i, 7));
                stmt1.setInt(9,campeonatoid);   
                stmt1.executeUpdate();
                }
            
            }
            rs22.close();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
         
    }
    
    
    public List<tabela> read(){
    
     Connection con = ConnectionFactory.getConnection();
     
     PreparedStatement stmt = null;
     ResultSet rs = null;
     
    
        List<tabela> tabelas = new ArrayList<>();
     
        try {
            stmt = con.prepareStatement("SELECT * FROM `tabela` WHERE 1 ORDER BY pontos DESC ");
            rs= stmt.executeQuery();
         
            while (rs.next()) {                
                
              tabela t = new tabela();
 
              t.setnomes(rs.getString("time"),0);
              t.setTabela(0, 0, rs.getInt("pontos"));
              t.setTabela(0, 1, rs.getInt("jogos"));
              t.setTabela(0, 2, rs.getInt("vitorias"));
              t.setTabela(0, 3, rs.getInt("derrotas"));
              t.setTabela(0, 4, rs.getInt("empates"));
              t.setTabela(0, 5, rs.getInt("gols_feitos"));
              t.setTabela(0, 6, rs.getInt("gols_sofridos"));
              t.setTabela(0, 7, rs.getInt("saldo_gols"));          
              tabelas.add(t);
            }  
            
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
        ConnectionFactory.closeConnection(con, stmt, rs);
        
        }
              
        return tabelas;

    }
     
public void removedados(){
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tabela set pontos=0,jogos=0,vitorias=0,derrotas=0,empates=0,gols_feitos=0,gols_sofridos=0 ,saldo_gols=0 WHERE 1");
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados removidos com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
         ConnectionFactory.closeConnection(con, stmt);
        
        }    
    }
    
public List<tabela> pesquisaTabelaPorAno(int a){
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        List<tabela> resultado = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM tabela,campeonatos where (tabela.campeonatoid = campeonatos.campeonatoid) and campeonatos.ano = ? ");
            stmt.setInt(1, a);
             rs = stmt.executeQuery();
   
            
            while (rs.next()) {                
                
              tabela t = new tabela();
              t.setnomes(rs.getString("time"),0);
              t.setTabela(0, 0, rs.getInt("pontos"));
              t.setTabela(0, 1, rs.getInt("jogos"));
              t.setTabela(0, 2, rs.getInt("vitorias"));
              t.setTabela(0, 3, rs.getInt("derrotas"));
              t.setTabela(0, 4, rs.getInt("empates"));
              t.setTabela(0, 5, rs.getInt("gols_feitos"));
              t.setTabela(0, 6, rs.getInt("gols_sofridos"));
              t.setTabela(0, 7, rs.getInt("saldo_gols"));        
              
              resultado.add(t);
            }  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
         ConnectionFactory.closeConnection(con, stmt,rs);
        
        }    
        return resultado;
    }

public void pesquisaAno(int ano){
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        List<tabela> resultado = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM campeonatos where campeonatos.ano = ? ");
            stmt.setInt(1, ano);
            rs = stmt.executeQuery();
   
            if(rs.next()){
                
                           
              //passar id do campeonato dentro do insert da tabela
              int idCampeonato = rs.getInt("campeonatoid"); 
              this.campeonatoid = idCampeonato;
             
              // insert em tabela 
             }else{
                Connection con1 = ConnectionFactory.getConnection(); 
                PreparedStatement stmt1 = null;
                stmt1 = con.prepareStatement("insert into campeonatos(ano) values(?)");
                stmt1.setInt(1,ano);
                stmt1.executeUpdate();
                System.out.println("insert");
                 
                Connection con2 = ConnectionFactory.getConnection();
                PreparedStatement stmt2 = null;    
                ResultSet rs2 = null;
                stmt2 = con2.prepareStatement("SELECT * FROM campeonatos where campeonatos.ano = ? ");
                stmt2.setInt(1, ano);    
                rs2 = stmt2.executeQuery();
                System.out.println("select");
                 if (rs2.next()) { 
             
                 int idCampeonato = rs2.getInt("campeonatoid");
                
                 this.campeonatoid = idCampeonato;
                 
              }
               
         } 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
       
         ConnectionFactory.closeConnection(con, stmt, rs);
         
        
        }
        try {
        
    } catch (Exception e) {
    }
       
    }
public List<tabela> pesquisaTabelaPorTime(String a){
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        List<tabela> resultado = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM tabela where tabela.time = ? ");
            stmt.setString(1, a);
            rs = stmt.executeQuery();
   
            
            while (rs.next()) {                
                
              tabela t = new tabela();
              t.setnomes(rs.getString("time"),0);
              t.setTabela(0, 0, rs.getInt("pontos"));
              t.setTabela(0, 1, rs.getInt("jogos"));
              t.setTabela(0, 2, rs.getInt("vitorias"));
              t.setTabela(0, 3, rs.getInt("derrotas"));
              t.setTabela(0, 4, rs.getInt("empates"));
              t.setTabela(0, 5, rs.getInt("gols_feitos"));
              t.setTabela(0, 6, rs.getInt("gols_sofridos"));
              t.setTabela(0, 7, rs.getInt("saldo_gols"));        
              
              resultado.add(t);
            }  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
         ConnectionFactory.closeConnection(con, stmt,rs);
        
        }    
        return resultado;
    }


public ArrayList<TabelaClass> pesquisarLider(){
    Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        List<tabela> resultado = new ArrayList<>();
        ResultSet rs = null;
        ArrayList<TabelaClass> resultadoBruto = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tabela as t,campeonatos as c where t.campeonatoid = c.campeonatoid ");
           
            rs = stmt.executeQuery();
   
            
            while (rs.next()) {                
              TabelaClass t = new TabelaClass();
              
             // tabela t = new tabela();
              t.setNome(rs.getString("time"));
              t.setPontos(rs.getInt("pontos"));
              t.setJogos(rs.getInt("jogos"));
              t.setVitorias(rs.getInt("vitorias"));
              t.setDerrotas(rs.getInt("derrotas"));
              t.setEmpates(rs.getInt("empates"));
              t.setGolsFeitos(rs.getInt("gols_feitos"));
              t.setGolsSofridos(rs.getInt("gols_sofridos"));
              t.setSaldoGols(rs.getInt("saldo_gols"));    
              t.setAno(rs.getInt("ano"));
             
              
              resultadoBruto.add(t);
            }  
            //apenas mostrando que a select esta funcionando.
          //  for(TabelaClass t : resultadoBruto){
            //  System.out.print(t.getNome()+ " - "+ t.getJogos()+ " - " + t.getVitorias() + " - " +t.getDerrotas() +"\n");

         //   }          
            
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
         ConnectionFactory.closeConnection(con, stmt,rs);
        
        }    
        return procurarLideres(resultadoBruto);    
}
private ArrayList<TabelaClass> procurarLideres(ArrayList<TabelaClass> lista){
    
   ArrayList<TabelaClass> times = new ArrayList<>();
  times = criarTimes();
    for(TabelaClass time : lista){
        if(time.getNome().equals("Palmeiras")){
            times.get(0).setTotalVitorias(times.get(0).getTotalVitorias()+time.getVitorias());
        }
        else if(time.getNome().equals("Sao Paulo")){
            times.get(1).setTotalVitorias(times.get(1).getTotalVitorias()+time.getVitorias());
        }
        else if(time.getNome().equals("Flamengo")){
            times.get(2).setTotalVitorias(times.get(2).getTotalVitorias()+time.getVitorias());
        }
         else if(time.getNome().equals("Gremio")){
            times.get(3).setTotalVitorias(times.get(3).getTotalVitorias()+time.getVitorias());
        }
        }
  


    //Ordenando vitorias - BubleSort - Depois desse trecho os times estao por ordem crescente de vitorias
       for(int i = 0; i < times.size(); i++){
           for(int j = 0; j <times.size()-1;j++ ){
               if(times.get(j).getTotalVitorias() > times.get(j+1).getTotalVitorias()){
               TabelaClass aux = times.get(j+1);
               times.set(j+1,times.get(j));               
               times.set(j,aux);                 
               }
           }           
       } 
    
    
    
    return times;

}

private ArrayList<TabelaClass> criarTimes(){
    ArrayList<TabelaClass> times = new ArrayList<>();
    times.add(new TabelaClass("Palmeiras"));
    times.add(new TabelaClass("Sao Paulo"));
    times.add(new TabelaClass("Flamengo"));
    times.add(new TabelaClass("Gremio"));

    return times;
}
}
//teste2