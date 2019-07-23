
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import model.Vendas;
import util.ConexaoBD;

public class usuarioRep extends Usuario{
    private static final String INSERT = "insert into usuario (nome, cargo, login, senha) values (?,?,?,?)";

    private static final String SELECT = "select idusuario,nome,cargo,login,senha from usuario order by nome";

    private static final String DELETE = "delete from usuario where login = ?";

    private static final String UPDATE = "update usuario set nome = ?, cargo = ?, login = ?, senha = ? where login = ?";
    
    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;
    
    public void adicionar(Usuario usuarios) {
        
        try {
            
            pstm = connection.prepareStatement(INSERT);
            pstm.setString(1, usuarios.getNome());
            pstm.setString(2, usuarios.getCargo());
            pstm.setString(3, usuarios.getLogin());
            pstm.setString(4, usuarios.getSenha());
            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    public void alterar(Usuario usuarios) {
        
        try {
            
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, usuarios.getNome());
            pstm.setString(2, usuarios.getCargo());
            pstm.setString(3, usuarios.getLogin());
            pstm.setString(4, usuarios.getSenha());
            pstm.setString(5, usuarios.getLogin());

            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    
    public void selecionar(Usuario usuarios) {
        
        try {
            
            pstm = connection.prepareStatement(SELECT);
            pstm.setString(1, usuarios.getNome());
            pstm.setString(2, usuarios.getCargo());
            pstm.setString(3, usuarios.getLogin());
            pstm.setString(4, usuarios.getSenha());
            pstm.setString(5, usuarios.getLogin());

            pstm.execute();
            pstm.close();
        
            System.out.println("Concluido!");

        }catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }
    public void excluir(Usuario usuarios) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setString(3, usuarios.getLogin());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }

    }

    public Usuario checarLogin(String Login,String Senha) {    
    
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;
        Usuario usuario = new Usuario();
            
        try {

            stmt = connection.prepareStatement("SELECT * FROM usuario WHERE login = ? and senha = ?");
            stmt.setString(1, Login);
            stmt.setString(2, Senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setLogin(rs.getString("login"));
                usuario.setCargo(rs.getString("cargo"));
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar encontrar o usuario: " + ex.getMessage());
        } 
        return usuario;
    }

   public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Usuario u = new Usuario();

                u.setIdusuario(res.getInt("idusuario"));
                u.setNome(res.getString("nome"));
                u.setSenha(res.getString("senha"));
                u.setLogin(res.getString("login"));
                u.setCargo(res.getString("cargo"));
                
                usuarios.add(u);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os estudantes do banco: " + ex.getMessage());
        }
        return usuarios;
    }
}



