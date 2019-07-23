package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import util.ConexaoBD;

public class FornecedorRep extends Fornecedor {

    private static final String INSERT = "insert into fornecedor (codFornecedor, nomeFornecedor, telefone, cnpj) values (?,?,?,?);";

    private static final String SELECT = "select codFornecedor, nomeFornecedor, telefone, cnpj from fornecedor order by nomeFornecedor";

    private static final String DELETE = "delete from fornecedor where CodFornecedor = ?";

    private static final String UPDATE = "update fornecedor set nomeFornecedor = ?, telefone = ?, cnpj = ? where CodFornecedor = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public void adicionar(Fornecedor fornecedor) {

        try {

            pstm = connection.prepareStatement(INSERT);
            pstm.setInt(1, fornecedor.getCodFornecedor());
            pstm.setString(2, fornecedor.getNomeFornecedor());
            pstm.setString(3, fornecedor.getTelefone());
            pstm.setString(4, fornecedor.getCnpj());
            pstm.execute();
            pstm.close();

            System.out.println("Concluido!");

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void alterar(Fornecedor fornecedor) {

        try {

            pstm = connection.prepareStatement(UPDATE);
            
            pstm.setString(1, fornecedor.getNomeFornecedor());
            pstm.setString(2, fornecedor.getTelefone());
            pstm.setString(3, fornecedor.getCnpj());
            pstm.setInt(4, fornecedor.getCodFornecedor());
            pstm.execute();
            pstm.close();

            System.out.println("Concluido!");

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void excluir(Fornecedor fornecedor) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, fornecedor.getCodFornecedor());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }

    }

    public List<Fornecedor> listar() {
        List<Fornecedor> fornecedor = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Fornecedor p = new Fornecedor();

                p.setCodFornecedor(res.getInt("codFornecedor"));
                p.setNomeFornecedor(res.getString("nomeFornecedor"));
                p.setTelefone(res.getString("telefone"));
                p.setCnpj(res.getString("cnpj"));
                fornecedor.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os fornecedores do banco: " + ex.getMessage());
        }
        return fornecedor;
    }
}
