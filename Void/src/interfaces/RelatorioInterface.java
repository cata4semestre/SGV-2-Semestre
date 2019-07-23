package interfaces;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Relatorio;
import repository.RelatorioRep;

public class RelatorioInterface extends AbstractTableModel {

    RelatorioRep prep = new RelatorioRep();
    List<Relatorio> relatorios;

    public RelatorioInterface() {
        relatorios = prep.listar();
    }

    public RelatorioInterface(List<Relatorio> relatorios) {
        this.relatorios = relatorios;
    }

    @Override
    public int getRowCount() {
        return relatorios.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return relatorios.get(rowIndex).getCodProduto();
            case 1:
                return relatorios.get(rowIndex).getNomeProduto();
            case 2:
                return relatorios.get(rowIndex).getQtdProduto();
            case 3:
                return relatorios.get(rowIndex).getPrecoProduto();
            case 4:
                return relatorios.get(rowIndex).getNomeCliente();
            case 5:
                return relatorios.get(rowIndex).getTotalVendas();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Código Relatorio";
            case 1:
                return "Nome Produto";
            case 2:
                return "Quantidade Produto";
            case 3:
                return "Preço do Produto";
            case 4:
                return "Nome do Cliente";
            case 5: 
                return "Total Vendas";
        }
        return null;
    }

}
