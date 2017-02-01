
package br.com.fatec.livrariadigital.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_detalheslivro")
public class Detalhes implements Serializable {
    private int idDetalhes;
    private String resumo;
    private String indice;
    private String formato;
    private int numPaginas;
    private Date dataDePublicacao;
    

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "N_idDetaLi")
    public int getIdDetalhes() {
        return idDetalhes;
    }

    public void setIdDetalhes(int idDetalhes) {
        this.idDetalhes = idDetalhes;
    }
    
    
    
    @Column(name = "C_resuLivr")
    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    
    @Column(name = "C_indiLivr",length = 1000)
    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }
    
    @Column(name = "C_formLivr")
    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    @Column(name = "N_numePagi")
    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }
    
    @Column(name = "D_dataPubl")
    public Date getDataDePublicacao() {
        return dataDePublicacao;
    }

    public void setDataDePublicacao(Date dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }
    
 

    public Detalhes() {
    }

    public Detalhes(String resumo, String indice, String formato, int numPaginas, Date dataDePublicacao) {
        this.resumo = resumo;
        this.indice = indice;
        this.formato = formato;
        this.numPaginas = numPaginas;
        this.dataDePublicacao = dataDePublicacao;
    }

   
    
 
    
}
