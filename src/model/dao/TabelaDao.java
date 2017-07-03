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
    
    /**
     *
     * @param t
     */
    public void create(tabela t){
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tabela set pontos = pontos + ? ,vitorias= vitorias + ? ,derrotas= derrotas + ?,empates= empates + ?"
                    + " ,gols_feitos = gols_feitos + ?, gols_sofridos = gols_sofridos + ?, saldo_gols = saldo_gols + ?, jogos = jogos +1 WHERE time=?");
            for (int i = 0; i < 4; i++) {

            stmt.setInt(1, t.getTabela(i, 0));
            stmt.setInt(2, t.getTabela(i, 2));
            stmt.setInt(3, t.getTabela(i, 3));
            stmt.setInt(4, t.getTabela(i, 4));
            stmt.setInt(5, t.getTabela(i, 5));
            stmt.setInt(6, t.getTabela(i, 6));
            stmt.setInt(7, t.getTabela(i, 7));
            stmt.setString(8, t.getnomes(i));
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
    
public void pesquisaTabelaPorAno(){
        Connection con = ConnectionFactory.getConnection();
       
        PreparedStatement stmt = null;
        List<tabela> resultado = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM tabela,campeonatos where (tabela.campeonatoid = campeonatos.campeonatoid) and campeonatos.ano = ? ");
            stmt.executeQuery();
            
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
    }
}
//teste