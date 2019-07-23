package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Relatorio;

import model.Vendas;
import util.ConexaoBD;

public class RelatorioRep extends Relatorio {

    private static final String INSERT = "insert into relatorio (codProduto, nomeProduto, precoProduto, qtdProduto, nomeCliente, totalVendas) values (?,?,?,?,?,?);";

    private static final String SELECT = "select codProduto, nomeProduto, precoProduto, qtdProduto, nomeCliente, totalVendas from relatorio order by nomeProduto";

    private static final String BUSCA_NOME = "select codProduto, nomeProduto, precoProduto, qtdProduto, nomeCliente, totalVendas from relatorio WHERE nomeProduto like ? order by nomeProduto";

    private static final String DELETE = "delete from relatorio where codProduto = ?";

    private static final String UPDATE = "update relatorio set nomeProduto = ?, codFornecedor = ?, precoProduto = ?, qtdProduto = ?, totalVendas = ? where codProduto = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public void adicionar(Relatorio relatorios) {

        try {

            pstm = connection.prepareStatement(INSERT);
            pstm.setInt(1, relatorios.getCodProduto());
            pstm.setString(2, relatorios.getNomeProduto());
            pstm.setBigDecimal(3, relatorios.getPrecoProduto());
            pstm.setInt(4, relatorios.getQtdProduto());
            pstm.setString(5, relatorios.getNomeCliente());
            pstm.setDouble(6, relatorios.getTotalVendas());
            pstm.execute();
            pstm.close();

            System.out.println("Concluido!");

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public List<Relatorio> buscarPorNome(String nome) {
        List<Relatorio> relatorios = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(BUSCA_NOME);
            pstm.setString(1, "%" + nome + "%");

            res = pstm.executeQuery();

            while (res.next()) {
                Relatorio p = new Relatorio();

                p.setCodProduto(res.getInt("codProduto"));
                p.setNomeProduto(res.getString("nomeProduto"));
                p.setPrecoProduto(res.getBigDecimal("precoProduto"));
                p.setQtdProduto(res.getInt("qtdProduto"));
                p.setNomeCliente(res.getString("nomeCliente"));
                p.setTotalVendas(res.getDouble("totalVendas"));

                relatorios.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Relatorios do banco: " + ex.getMessage());
        }
        return relatorios;
    }

    public void alterar(Relatorio relatorios) {

        try {

            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, relatorios.getNomeProduto());
            pstm.setBigDecimal(3, relatorios.getPrecoProduto());
            pstm.setInt(5, relatorios.getQtdProduto());
            pstm.setInt(6, relatorios.getCodProduto());
            pstm.setString(7, relatorios.getNomeCliente());
            pstm.setDouble(8, relatorios.getTotalVendas());
            pstm.execute();
            pstm.close();

            System.out.println("Concluido!");

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void alterarsalvar(Relatorio relatorios) {

        try {

            pstm = connection.prepareStatement("update relatorio set qtdProduto = ? where codProduto = ?");
            pstm.setInt(1, relatorios.getQtdProduto());
            pstm.setInt(2, relatorios.getCodProduto());
            pstm.execute();
            pstm.close();

            System.out.println("Concluido!");

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void excluir(Relatorio relatorios) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, relatorios.getCodProduto());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar Deletar: " + ex.getMessage());
        }

    }

    public List<Relatorio> listar() {
        List<Relatorio> relatorios = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Relatorio p = new Relatorio();

                p.setCodProduto(res.getInt("codProduto"));
                p.setNomeProduto(res.getString("nomeProduto"));
                p.setPrecoProduto(res.getBigDecimal("precoProduto"));
                p.setQtdProduto(res.getInt("qtdProduto"));
                p.setNomeCliente(res.getString("nomeCliente"));
                p.setTotalVendas(res.getDouble("totalVendas"));

                relatorios.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Relatorios do banco: " + ex.getMessage());
        }
        return relatorios;
    }

    public List<Relatorio> readForDesc(String desc) {

        Connection con = ConexaoBD.conectarBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Relatorio> relatorios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM relatorio WHERE nomeProduto LIKE ?");
            stmt.setString(1, "%" + desc + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Relatorio relatorio = new Relatorio();

                relatorio.setCodProduto(rs.getInt("codProduto"));
                relatorio.setNomeProduto(rs.getString("nomeProduto"));
                relatorio.setPrecoProduto(rs.getBigDecimal("precoProduto"));
                relatorio.setNomeCliente(rs.getString("nomeCliente"));
                relatorio.setTotalVendas(rs.getDouble("totalVendas"));
                relatorios.add(relatorio);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os Relatorios do banco: " + ex.getMessage());
        }

        return relatorios;
    }
}
