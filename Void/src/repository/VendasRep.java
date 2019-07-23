
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vendas;
import util.ConexaoBD;


public class VendasRep extends Vendas{    
    private static final String INSERT = "insert into vendas (codProduto, nomeProduto, precoProduto, qtdProduto, nomeCliente) values (?,?,?,?,?)";

    private static final String SELECT = "select idvendas, codProduto, nomeProduto, precoProduto, qtdProduto, nomeCliente from vendas order by nomeProduto";

    private static final String BUSCA_NOME = "select codProduto, nomeProduto, precoProduto, qtdProduto, nomeCliente from vendas WHERE nomeProduto like ? order by nomeProduto";

    private static final String DELETE = "delete from vendas where idvendas = ?";

    private static final String UPDATE = "update vendas set nomeProduto = ?, codFornecedor = ?, precoProduto = ?, qtdProduto = ? where codProduto = ?";
    
    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;
    
    public void adicionar(Vendas vendas) {
        
        try {
            
            pstm = connection.prepareStatement(INSERT);
            pstm.setInt(1, vendas.getCodProduto());
            pstm.setString(2, vendas.getNomeProduto());
            pstm.setBigDecimal(3, vendas.getPrecoProduto());
            pstm.setInt(4, vendas.getQtdProduto());
            pstm.setString(5, vendas.getNomeCliente());
            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public List<Vendas> buscarPorNome(String nome) {
        List<Vendas> vendas = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(BUSCA_NOME);
            pstm.setString(1, "%" + nome + "%");
            
            res = pstm.executeQuery();

            while (res.next()) {
                Vendas p = new Vendas();
                
                p.setCodProduto(res.getInt("codProduto"));
                p.setNomeProduto(res.getString("nomeProduto"));
                p.setPrecoProduto(res.getBigDecimal("precoProduto"));
                p.setQtdProduto(res.getInt("qtdProduto"));
                p.setNomeCliente(res.getString("nomeCliente"));
                
                vendas.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Relatorios do banco: " + ex.getMessage());
        }
        return vendas;
    }
    
    public void alterar(Vendas vendas) {
        
        try {
            
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, vendas.getNomeProduto());
            pstm.setBigDecimal(3, vendas.getPrecoProduto());
            pstm.setInt(5, vendas.getQtdProduto());
            pstm.setInt(6, vendas.getCodProduto());
            pstm.setString(7, vendas.getNomeCliente());
            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    public void alterarsalvar(Vendas vendas) {
        
        try {
            
            pstm = connection.prepareStatement("update vendas set qtdProduto = ? where codProduto = ?");
            pstm.setInt(1, vendas.getQtdProduto());
            pstm.setInt(2, vendas.getCodProduto());
            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public void excluir(Vendas vendas) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, vendas.getidvendas());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar Deletar: " + ex.getMessage());
        }

    }

    public List<Vendas> listar() {
        List<Vendas> vendas = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Vendas p = new Vendas();
                p.setidvendas(res.getInt("idvendas"));
                p.setCodProduto(res.getInt("codProduto"));
                p.setNomeProduto(res.getString("nomeProduto"));
                p.setPrecoProduto(res.getBigDecimal("precoProduto"));
                p.setQtdProduto(res.getInt("qtdProduto"));
                p.setNomeCliente(res.getString("nomeCliente"));
                vendas.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os estudantes do banco: " + ex.getMessage());
        }
        return vendas;
    }
    public List<Vendas> readForDesc(String desc) {

        Connection con = ConexaoBD.conectarBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Vendas> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM vendas WHERE nomeProduto LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas venda = new Vendas();

                venda.setCodProduto(rs.getInt("codProduto"));
                venda.setNomeProduto(rs.getString("nomeProduto"));
                venda.setPrecoProduto(rs.getBigDecimal("precoProduto"));
                venda.setNomeCliente(rs.getString("nomeCliente"));
                vendas.add(venda);
            }

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os estudantes do banco: " + ex.getMessage());
        }

        return vendas;
    }
}

