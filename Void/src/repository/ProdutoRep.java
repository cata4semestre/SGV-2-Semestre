
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import util.ConexaoBD;


public class ProdutoRep extends Produto{    
    private static final String INSERT = "insert into produto (codProduto, nomeProduto, codFornecedor, precoCusto, precoVenda, qtdEstoque) values (?,?,?,?,?,?);";

    private static final String SELECT = "select codProduto, nomeProduto, codFornecedor, precoCusto, precoVenda, qtdEstoque from produto order by nomeProduto";

    private static final String BUSCA_NOME = "select codProduto, nomeProduto, codFornecedor, precoCusto, precoVenda, qtdEstoque from produto WHERE nomeProduto like ? order by nomeProduto";

    private static final String DELETE = "delete from produto where codProduto = ?";

    private static final String UPDATE = "update produto set nomeProduto = ?, codFornecedor = ?, precoCusto = ?, precoVenda = ?, qtdEstoque = ? where codProduto = ?";
    
    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;
    
    public void adicionar(Produto produtos) {
        
        try {
            
            pstm = connection.prepareStatement(INSERT);
            pstm.setInt(1, produtos.getCodProduto());
            pstm.setString(2, produtos.getNomeProduto());
            pstm.setInt(3, produtos.getCodFornecedor());
            pstm.setBigDecimal(4, produtos.getPrecoCusto());
            pstm.setBigDecimal(5, produtos.getPrecoVenda());
            pstm.setInt(6, produtos.getQtdEstoque());
            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public List<Produto> buscarPorNome(String nome) {
        List<Produto> produtos = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(BUSCA_NOME);
            pstm.setString(1, "%" + nome + "%");
            
            res = pstm.executeQuery();

            while (res.next()) {
                Produto p = new Produto();
                
                p.setCodProduto(res.getInt("codProduto"));
                p.setNomeProduto(res.getString("nomeProduto"));
                p.setCodFornecedor(res.getInt("codFornecedor"));
                p.setPrecoCusto(res.getBigDecimal("precoCusto"));
                p.setPrecoVenda(res.getBigDecimal("precoVenda"));
                p.setQtdEstoque(res.getInt("qtdEstoque"));
                
                produtos.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os estudantes do banco: " + ex.getMessage());
        }
        return produtos;
    }
    
    public void alterar(Produto produtos) {
        
        try {
            
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, produtos.getNomeProduto());
            pstm.setInt(2, produtos.getCodFornecedor());
            pstm.setBigDecimal(3, produtos.getPrecoCusto());
            pstm.setBigDecimal(4, produtos.getPrecoVenda());
            pstm.setInt(5, produtos.getQtdEstoque());
            pstm.setInt(6, produtos.getCodProduto());
            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public void alterarsalvar(Produto produtos) {
        
        try {
            
            pstm = connection.prepareStatement("update produto set qtdEstoque = ? where codProduto = ?");
            pstm.setInt(1, produtos.getQtdEstoque());
            pstm.setInt(2, produtos.getCodProduto());
            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public void excluir(Produto produtos) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, produtos.getCodProduto());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }

    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Produto p = new Produto();
                
                p.setCodProduto(res.getInt("codProduto"));
                p.setNomeProduto(res.getString("nomeProduto"));
                p.setCodFornecedor(res.getInt("codFornecedor"));
                p.setPrecoCusto(res.getBigDecimal("precoCusto"));
                p.setPrecoVenda(res.getBigDecimal("precoVenda"));
                p.setQtdEstoque(res.getInt("qtdEstoque"));
                
                produtos.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os estudantes do banco: " + ex.getMessage());
        }
        return produtos;
    }
    public List<Produto> readForDesc(String desc) {

        Connection con = ConexaoBD.conectarBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE nomeProduto LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setCodProduto(rs.getInt("codProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setPrecoCusto(rs.getBigDecimal("precoCusto"));
                produto.setPrecoVenda(rs.getBigDecimal("precoVenda"));
                produtos.add(produto);
            }

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os estudantes do banco: " + ex.getMessage());
        }

        return produtos;
    }
}

