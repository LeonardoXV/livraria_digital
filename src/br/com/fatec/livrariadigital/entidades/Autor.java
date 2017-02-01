
package br.com.fatec.livrariadigital.entidades;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.transaction.Transactional;

@Entity
@Table(name = "tb_autores")
public class Autor implements Serializable {
    
    
    private int idAutor;
    private String nome;
    private Date dataNasce; 
    private Date dataFale;
    private String localNasc;
    private String localMorte;
    private String biografia;
    private List<Livro> livros;


    public Autor() {}

    public Autor(String nome, Date dataNasce, Date dataFale, String localNasc, String localMorte, String biografia) {
        this.nome = nome;
        this.dataNasce = dataNasce;
        this.dataFale = dataFale;
        this.localNasc = localNasc;
        this.localMorte = localMorte;
        this.biografia = biografia;
     
        
        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_IdAutor")
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int IdAutor) {
        this.idAutor = IdAutor;
    }
    
    
    @Column(name = "C_nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }
    
    @Column(name = "D_datanasc")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDataNasce() {
        return dataNasce;
    }

    public void setDataNasce(Date dataNasce) {
        this.dataNasce = dataNasce;
    }
    
    @Column(name = "D_dataFa")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDataFale() {
        return dataFale;
    }

    public void setDataFale(Date dataFale) {
        this.dataFale = dataFale;
    }
    
    @Column(name = "C_localnasx")
    public String getLocalNasc() {
        return localNasc;
    }

    public void setLocalNasc(String localNasc) {
        this.localNasc = localNasc;
    }
    
    @Column(name = "C_localMo")
    public String getLocalMorte() {
        return localMorte;
    }

    public void setLocalMorte(String localMorte) {
        this.localMorte = localMorte;
    }
    
    @Column(name = "C_bibliogra",length = 1000)
    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
    @ManyToMany(mappedBy = "autores")
    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
    
    
    

}

