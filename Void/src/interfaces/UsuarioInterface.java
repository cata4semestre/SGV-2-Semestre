
package interfaces;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import repository.usuarioRep;
import model.Usuario;

public class UsuarioInterface extends AbstractTableModel{
    
     usuarioRep uso = new usuarioRep();
     List<Usuario> usuario;

    public UsuarioInterface() {
            usuario = uso.listar();
    }
    public UsuarioInterface(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    @Override
    public int getRowCount() {
        return usuario.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
             case 0:
                return usuario.get(rowIndex).getNome();
            case 1:
                return usuario.get(rowIndex).getCargo();
            case 2:
                return usuario.get(rowIndex).getLogin();
            case 3:
                return usuario.get(rowIndex).getSenha();
        }
        return null;
        }
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Nome";
            case 1:
                return "Cargo";
            case 2:
                return "Login";
            case 3:
                return "Senha";
        }
        return null;
    }
}
