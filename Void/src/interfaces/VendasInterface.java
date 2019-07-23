package interfaces;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Vendas;
import repository.VendasRep;

public class VendasInterface extends AbstractTableModel {

    VendasRep prep = new VendasRep();
    List<Vendas> vendas;

    public VendasInterface() {
        vendas = prep.listar();
    }

    public VendasInterface(List<Vendas> vendas) {
        this.vendas = vendas;
    }

    @Override
    public int getRowCount() {
        return vendas.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return vendas.get(rowIndex).getidvendas();
            case 1:
                return vendas.get(rowIndex).getCodProduto();
            case 2:
                return vendas.get(rowIndex).getNomeProduto();
            case 3:
                return vendas.get(rowIndex).getQtdProduto();
            case 4:
                return vendas.get(rowIndex).getPrecoProduto();
            case 5:
                return vendas.get(rowIndex).getNomeCliente();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id Vendas";
            case 1:
                return "Código Relatorio";
            case 2:
                return "Nome Produto";
            case 3:
                return "Quantidade Produto";
            case 4:
                return "Preço do Produto";
            case 5:
                return "Nome do Cliente";
        }
        return null;
    }

}
