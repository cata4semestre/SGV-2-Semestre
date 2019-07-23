
package interfaces;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import repository.ClienteRep;
import model.Cliente;

public class ClienteInterface extends AbstractTableModel{

    ClienteRep cli = new ClienteRep();
    List<Cliente> clientes;
    
    public ClienteInterface() {
        clientes = cli.listar();
    }
    public ClienteInterface(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
                case 0:
                return clientes.get(rowIndex).getCodCliente();
            case 1:
                return clientes.get(rowIndex).getNomeCliente();
            case 2:
                return clientes.get(rowIndex).getCpf();
            case 3:
                return clientes.get(rowIndex).getAv();  
            case 4:
                return clientes.get(rowIndex).getCidade();
        }
        return null;
        }
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Código Cliente";
            case 1:
                return "Nome ";
            case 2:
                return "CPF";
            case 3:
                return "CAv/Rua"; 
            case 4:
                return "Cidade";  
        }
        return null;
    }
    }
    
    
    
