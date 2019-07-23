
package interfaces;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import repository.SolicitacaoRep;
import model.Solicitacao;

public class SolicitacaoInterface extends AbstractTableModel{
    
    SolicitacaoRep solic = new SolicitacaoRep();
     List<Solicitacao> solicitacao;

    public SolicitacaoInterface() {
        solicitacao = solic.listar();
    }
    
    public SolicitacaoInterface(List<Solicitacao> solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public int getRowCount() {
        return solicitacao.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
        
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
             case 0:
                return solicitacao.get(rowIndex).getCodProduto();
            case 1:
                return solicitacao.get(rowIndex).getNomeProduto();
            case 2:
                return solicitacao.get(rowIndex).getPrecoCusto();
            case 3:
                return solicitacao.get(rowIndex).getPrecoVenda();
            case 4:
                return solicitacao.get(rowIndex).getQtdProduto();
            case 5:
                return solicitacao.get(rowIndex).getStatus();
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
            case 4:
                return "Quantidade"; 
            case 5:
                return "Status "; 
        }
        return null;
    }
}
