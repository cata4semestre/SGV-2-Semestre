package repository;

import com.sun.scenario.effect.impl.prism.PrCropPeer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Solicitacao;
import util.ConexaoBD;

public class SolicitacaoRep extends Solicitacao {
    
    private static final String INSERT = "insert into solicitacao (codProduto, nomeProduto, precoCusto, precoVenda, qtdProduto, status) values (?,?,?,?,?,?);";
    
    private static final String SELECT = "select codProduto, nomeProduto, precoCusto, precoVenda, qtdProduto, status from solicitacao order by nomeProduto";
    
    private static final String DELETE = "delete from solicitacao where codProduto = ?";
    
    private static final String UPDATE = "update solicitacao set nomeProduto = ?, precoCusto = ?, precoVenda = ?, qtdProduto = ?, status = ? where CodProduto = ?";
    
    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;
    
    public void adicionar(Solicitacao solicitacao) {
        
        try {
            
            pstm = connection.prepareStatement(INSERT);
            pstm.setInt(1, solicitacao.getCodProduto());
            pstm.setString(2, solicitacao.getNomeProduto());
            pstm.setBigDecimal(3, solicitacao.getPrecoCusto());
            pstm.setBigDecimal(4, solicitacao.getPrecoVenda());
            pstm.setInt(5, solicitacao.getQtdProduto());
            pstm.setString(6, solicitacao.getStatus());
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Concluido!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public void alterar(Solicitacao solicitacao) {
        
        try {
            
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, solicitacao.getNomeProduto());
            pstm.setBigDecimal(2, solicitacao.getPrecoCusto());
            pstm.setBigDecimal(3, solicitacao.getPrecoVenda());
            pstm.setInt(4, solicitacao.getQtdProduto());
            pstm.setString(5, solicitacao.getStatus());
            pstm.setInt(6, solicitacao.getCodProduto());

            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Concluido!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void alterarSolicitacao(Solicitacao solicitacao) {
        
        try {
            
            pstm = connection.prepareStatement("update solicitacao set status = ? where codProduto = ?");
            
            pstm.setString(1, solicitacao.getStatus());
            pstm.setInt(2, solicitacao.getCodProduto());
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Concluido!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public void excluir(Solicitacao solicitacao) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, solicitacao.getCodProduto());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
        
    }
    
    public List<Solicitacao> listar() {
        List<Solicitacao> solicitacao = new ArrayList<>();
        ResultSet res;
        
        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();
            
            while (res.next()) {
                Solicitacao p = new Solicitacao();
                
                p.setCodProduto(res.getInt("codProduto"));
                p.setNomeProduto(res.getString("nomeProduto"));
                p.setPrecoCusto(res.getBigDecimal("precoCusto"));
                p.setPrecoVenda(res.getBigDecimal("precoVenda"));
                p.setQtdProduto(res.getInt("qtdProduto"));
                p.setStatus(res.getString("status"));
                
                solicitacao.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Produtos do banco: " + ex.getMessage());
        }
        return solicitacao;
    }
}
