
package br.com.fatec.livrariadigital.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "tb_categorias")
public class Categoria implements Serializable {
    private int id;
    private String nome;
    private List<Livro>livros;
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_idCatego")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "C_tipoCate")
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
   

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }
    
    @ManyToMany(mappedBy = "categorias",fetch = FetchType.LAZY)
    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
    
    
    
    
    
    
    
    
    
    
}
