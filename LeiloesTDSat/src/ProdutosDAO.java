/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        PreparedStatement st = null;
    try {int status;
        st = conn.prepareStatement("INSERT INTO uc11 (nome, valor, status) VALUES (?, ?, ?)");
        
        st.setString(1, produto.getNome());
        st.setDouble(2, produto.getValor());  
        st.setString(3, produto.getStatus());
        
        status = st.executeUpdate();
        return status; 
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar: " + ex.getMessage());
        return ex.getErrorCode();
    }
        
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

