package br.com.fatec.livrariadigital.telasgui.controllers;
import br.com.fatec.livrariadigital.aplicacao.OperacoesDetalhe;
import br.com.fatec.livrariadigital.aplicacao.OperacoesLivro;
import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Categoria;
import br.com.fatec.livrariadigital.entidades.Detalhes;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class LivrosController extends ControllerGeral {
    
    private List<String> nomes=new ArrayList();
    private List elementos;
    private Livro livro=new Livro();
    private String entidade;
    private OperacoesLivro operacoesLivro=new OperacoesLivro(this.getEntityManagerFactory());
    private OperacoesDetalhe operacoesDetalhe=new OperacoesDetalhe(this.getEntityManagerFactory());
   
    
    public void CadastrarLivro(String resumo,String indice,String formato,int numPaginas,String dataPublicacao,double precoVenda, String Isbn, String titulo, 
        List<Categoria> categorias, Editora editora, List<Autor>autores, File imagem,int quantidade_livro,Double precoCusto){
        Detalhes detalhes=new Detalhes(resumo,indice,formato,numPaginas,new Date(dataPublicacao));
        Livro livro=new Livro(Isbn, titulo, categorias, autores, editora, imagem, detalhes, quantidade_livro, precoVenda,precoCusto);
        this.operacoesLivro.cadastrarNoBanco(livro);
    }
    
    public void preencerComboBox(String sql, ComboBox box){
        
        ObservableList<String> options;
        this.nomes=new ArrayList();
        if(sql.equals("editora")){
            List<Editora> editoras=this.consultaBasica(sql);
            for (Editora editora : editoras) {
                this.nomes.add(editora.getNomeFantasia());
            }
           
        }
        
        else if(sql.equals("categoria")){
            List<Categoria> categorias=this.consultaBasica(sql);
            
            for (Categoria categoria : categorias) {
              
                this.nomes.add(categoria.getNome());
            }
        }
        
        else if(sql.equals("livro")){
              List<Livro> livros=this.consultaBasica(sql);
            for (Livro livro: livros) {
                
                this.nomes.add(livro.getTitulo());
                
            }
        }
        
        else{
             List<Autor> autores=this.consultaBasica(sql);
            
            for (Autor autor : autores) {
                this.nomes.add(autor.getNome());
            }
        }
        options=FXCollections.observableArrayList(nomes);
        box.setItems(options);
    }
    
   
    

     public void deletar(String isbn){
        List<Livro>livros=this.consultaAvancada("Livro",isbn,"isbn");
        this.operacoesLivro.deletar(livros.get(0));
    }
     
     public void atualizarLivro(Livro livro,String resumo,String indice,String formato,int numPaginas,String dataPublicacao,double precoVenda, String Isbn, String titulo, 
        List<Categoria> categorias, Editora editora, List<Autor>autores, File imagem,double precoDeCusto,int quantidade){
        Date data=new Date(dataPublicacao);
        System.out.println("oi");
        livro.getDetahes().setDataDePublicacao(data);
        livro.getDetahes().setFormato(formato);
        livro.getDetahes().setIndice(indice);
        livro.getDetahes().setNumPaginas(numPaginas);
        livro.getDetahes().setResumo(resumo);
        livro.setAutores(autores);
        livro.setCategorias(categorias);
        livro.setImagem(imagem);
        livro.setIsbn(Isbn);
        livro.setPrecoDeVenda(precoVenda);
        livro.setTitulo(titulo);
        livro.setEditora(editora);
        livro.setPrecoDeCusto(precoDeCusto);
        livro.setQuantidade_livro(quantidade);
        this.operacoesLivro.atualizar(livro);
     }

    public OperacoesLivro getOperacoesLivro() {
        return operacoesLivro;
    }
     
     
}
