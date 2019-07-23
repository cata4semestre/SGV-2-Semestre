package interfaces;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Produto;
import repository.ProdutoRep;

public class ProdutoInterface extends AbstractTableModel {

    ProdutoRep prep = new ProdutoRep();
    List<Produto> produtos;    
    
    public ProdutoInterface() {
        produtos = prep.listar();
    }

    public ProdutoInterface(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return produtos.get(rowIndex).getCodProduto();
            case 1:
                return produtos.get(rowIndex).getNomeProduto();
            case 2:
                return produtos.get(rowIndex).getQtdEstoque();
            case 3:
                return produtos.get(rowIndex).getCodFornecedor();                
            case 4:
                return produtos.get(rowIndex).getPrecoCusto();                
            case 5:
                return produtos.get(rowIndex).getPrecoVenda();                
        }
        return null;    
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Código Produto";
            case 1:
                return "Nome Produto";
            case 2:
                return "Quantidade Estoque";
            case 3:
                return "Fornecedor";  
            case 4:
                return "Preço de Custo";  
            case 5:
                return "Preço de Venda";  
        }
        return null;
    }
    
    
}
