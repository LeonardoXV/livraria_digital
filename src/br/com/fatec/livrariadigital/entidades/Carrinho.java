package br.com.fatec.livrariadigital.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho implements Serializable{

    private int idCarrinho;
    private List<Livro>livros;

    public Carrinho() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_idCarrin")
    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }
    
    @ManyToMany
    @JoinTable(name = "Tb_LivrosCarrinho",joinColumns = {@JoinColumn(name = "N_idCarrin")},inverseJoinColumns = {@JoinColumn(name = "N_idLivro")})
    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Carrinho(List<Livro> livros) {
        this.livros = livros;
    }
    
    
    
}
