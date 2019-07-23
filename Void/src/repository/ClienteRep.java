package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.ConexaoBD;


public class ClienteRep extends Cliente{
    private static final String INSERT = "insert into cliente ( codCliente, nomeCliente, cpf, cidade,av) values (?,?,?,?,?);";

    private static final String SELECT = "select codCliente, nomeCliente, cpf, cidade, av from cliente order by codCliente";

    private static final String DELETE = "delete from cliente where codCliente = ?";

    private static final String UPDATE = "update cliente set nomeCliente = ?, cpf = ?, cidade = ?, av = ? where codCliente = ?";
    
    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;
    
    public void adicionar(Cliente clientes) {
        
        try {
            
            pstm = connection.prepareStatement(INSERT);
            pstm.setInt(1, clientes.getCodCliente());
            pstm.setString(2, clientes.getNomeCliente());
            pstm.setString(3, clientes.getCpf());
            pstm.setString(4, clientes.getCidade());
            pstm.setString(5, clientes.getAv());
            pstm.execute();
            pstm.executeUpdate();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    public void alterar(Cliente clientes) {
        
        try {
            
            pstm = connection.prepareStatement(UPDATE);
            
            pstm.setString(1, clientes.getNomeCliente());
            pstm.setString(2, clientes.getCpf());
            pstm.setString(3, clientes.getCidade());
            pstm.setString(4, clientes.getAv());
            pstm.setInt(5, clientes.getCodCliente());
            pstm.execute();
            pstm.executeUpdate();
            pstm.close();
            
            
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    public void excluir(Cliente clientes) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, clientes.getCodCliente());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }

    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Cliente c = new  Cliente();
                
                c.setCodCliente(res.getInt("codCliente"));
                c.setNomeCliente(res.getString("nomeCliente"));
                c.setCpf(res.getString("cpf"));
                c.setCidade(res.getString("cidade"));
                c.setAv(res.getString("av"));
                
                
                clientes.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os estudantes do banco: " + ex.getMessage());
        }
        return clientes;
    }
}