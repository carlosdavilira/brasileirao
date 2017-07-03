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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.tabela;

/**
 *
 * @author betim
 */
public class TabelaDao {
    public int campeonatoid=0;
    
    /**
     *
     * @param t
     */
    public void create(tabela t){
        
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tabela set pontos = pontos + ? ,vitorias= vitorias + ? ,derrotas= derrotas + ?,empates= empates + ?"
                    + " ,gols_feitos = gols_feitos + ?, gols_sofridos = gols_sofridos + ?, saldo_gols = saldo_gols + ?, jogos = jogos +1, campeonatoid = ? WHERE time=?");
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
            stmt.executeUpdate();
            }
   
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
         ConnectionFactory.closeConnection(con, stmt);
        
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
            
         ConnectionFactory.closeConnection(con, stmt);
        
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
   
            if(rs==null){
                
            if (rs.next()) {                
              //passar id do campeonato dentro do insert da tabela
              int idCampeonato = rs.getInt("campeonatoid");
              this.campeonatoid = idCampeonato;
             
              // insert em tabela 
       
            } }
            else
            {
                Connection con1 = ConnectionFactory.getConnection(); 
                PreparedStatement stmt1 = null;
                stmt1 = con.prepareStatement("insert into campeonatos(ano) values(?)");
                stmt1.setInt(1,ano);
                stmt1.executeUpdate();
                
                Connection con2 = ConnectionFactory.getConnection();
                PreparedStatement stmt2 = null;          
                stmt2 = con2.prepareStatement("SELECT * FROM campeonatos where campeonatos.ano = ? ");
                stmt2.setInt(1, ano);
                ResultSet rs2 = null;
                rs2 = stmt2.executeQuery();
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
}
//teste2