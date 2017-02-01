
package br.com.fatec.livrariadigital.entidades;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "tb_livros")
public class Livro implements Serializable {

    private int idLivro;
    private String isbn;
    private  String titulo;
    private List<Categoria> categorias;
    private List<Autor> autores;
    private Editora editora;
    private File imagem;
    private Detalhes detahes;
    private int quantidade_livro;
    private  double precoDeVenda;
    private double precoDeCusto;
    private List<ItemPedido> itens;
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_idLivro")
    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
 
    
    @Column(name = "C_isbnLivr",nullable = false)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    @Column(name = "C_tituLivr",nullable = false)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
 
    
    @ManyToMany
    @JoinTable(name="Tb_LivrosPorCategoria", joinColumns={@JoinColumn(name="N_idLivro")}, inverseJoinColumns={@JoinColumn(name="N_idCatego")})
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    

    @ManyToMany
    @JoinTable(name = "Tb_LivrosPorAutor",joinColumns = {@JoinColumn(name = "N_idLivro")},inverseJoinColumns = {@JoinColumn(name = "N_idAutor")})
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    
    @ManyToOne
    @JoinColumn(name = "N_idEditor",nullable = false)
    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    
    @Column(name="c_imgLivro",nullable = false)
    public File getImagem() {
        return imagem;
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }

    @Column(name = "quant_livro",nullable = false)
    public int getQuantidade_livro() {
        return quantidade_livro;
    }

    public void setQuantidade_livro(int quantidade_livro) {
        this.quantidade_livro = quantidade_livro;
    }
    
    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "N_idDetalhe", nullable = false)
    public Detalhes getDetahes() {
        return detahes;
    }

    public void setDetahes(Detalhes detahes) {
        this.detahes = detahes;
    }
    
    @Column(name = "N_precoVen",nullable = false)
    public double getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }
    
    @OneToMany(mappedBy = "livro")
    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
    
    @Column(name = "N_PreCoCu",nullable = false)
    public double getPrecoDeCusto() {
        return precoDeCusto;
    }

    public void setPrecoDeCusto(double precoDeCusto) {
        this.precoDeCusto = precoDeCusto;
    }
    

    public Livro() {}

    public Livro(String isbn, String titulo, List<Categoria> categorias, List<Autor> autores, Editora editora, File imagem, Detalhes detahes, int quantidade_livro, double precoDeVenda, double precoDeCusto) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.categorias = categorias;
        this.autores = autores;
        this.editora = editora;
        this.imagem = imagem;
        this.detahes = detahes;
        this.quantidade_livro = quantidade_livro;
        this.precoDeVenda = precoDeVenda;
        this.precoDeCusto = precoDeCusto;
    }


    
    
   
}
