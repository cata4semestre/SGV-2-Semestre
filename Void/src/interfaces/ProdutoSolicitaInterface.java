
package interfaces;

import java.util.List;
import model.Produto;
import repository.ProdutoRep;

import javax.swing.table.AbstractTableModel;

public class ProdutoSolicitaInterface extends AbstractTableModel{
     
        ProdutoRep solic = new ProdutoRep();
         List<Produto> solicitar;

    public ProdutoSolicitaInterface() {
        solicitar = solic.listar();
    }
    
    public ProdutoSolicitaInterface(List<Produto> solicitar) {
        this.solicitar = solicitar;
    }

    @Override
    public int getRowCount() {
        return solicitar.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
        
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return solicitar.get(rowIndex).getCodProduto();
            case 1:
                return solicitar.get(rowIndex).getNomeProduto();
            case 2:
                return solicitar.get(rowIndex).getPrecoCusto();
            case 3:
                return solicitar.get(rowIndex).getPrecoVenda();           
        }
        return null;
        }
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Código Produto";
            case 1:
                return "Nome do Produto";
            case 2:
                return "Preço de Custo";
            case 3:
                return "Preço de Venda"; 
        }
        return null;
    }
}
