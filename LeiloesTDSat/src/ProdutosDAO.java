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
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto) throws SQLException{
        this.conn = new conectaDAO().connectDB();
        PreparedStatement st = null;
    try {int status;
        st = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
        
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
        List<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM uc11";

        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return listagem;
    }
    
    public void venderproduto (ProdutosDTO p){
               String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "0665@180901Lb");
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setInt(1, p.getId());  
        int rowsUpdated = stmt.executeUpdate();
        
        return;  
        
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar o status do produto: " + e.getMessage());
        return;
        }
    }
    public List<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT id, nome, preco, status FROM produtos WHERE status = 'Vendido'";
    List<ProdutosDTO> produtosVendidos = new ArrayList<>();
    
    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
         
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("preco"));
            produto.setStatus(rs.getString("status"));
            produtosVendidos.add(produto);
        }
        
    } catch (SQLException e) {
        System.err.println("Erro ao listar produtos vendidos: " + e.getMessage());
    }
    
    return produtosVendidos;
}

    }
   
    
    
        


