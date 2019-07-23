/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;



/**
 *
 * @author Victor
 */

public class Vendas {

    private Integer idvendas;
    private Integer codProduto;
    private String nomeProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal precoProduto;
    private Integer qtdProduto;
    private String nomeCliente;

    public Vendas() {
    }

    public Vendas(Integer idrelatorio, Integer codProduto, String nomeProduto, BigDecimal precoProduto, Integer qtdProduto, String nomeCliente) {
        this.idvendas = idrelatorio;
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.qtdProduto = qtdProduto;
        this.nomeCliente = nomeCliente;
    }

    public Integer getidvendas() {
        return idvendas;
    }

    public void setidvendas(Integer idvendas) {
        this.idvendas = idvendas;
    }

    public Integer getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvendas != null ? idvendas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendas)) {
            return false;
        }
        Vendas other = (Vendas) object;
        if ((this.idvendas == null && other.idvendas != null) || (this.idvendas != null && !this.idvendas.equals(other.idvendas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeProduto;
    }
    
}
