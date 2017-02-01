    package br.com.fatec.livrariadigital.telasgui.controllers;
import br.com.fatec.livrariadigital.aplicacao.Imagem;
import br.com.fatec.livrariadigital.aplicacao.OperacoesAutor;
import br.com.fatec.livrariadigital.entidades.Informacoes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControllerGeral {
    
    private File file;
    private Imagem imagem=new Imagem();
    private String entidade;
    private List elementos=new ArrayList();
    EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("persistence");
    OperacoesAutor op=new OperacoesAutor(this.entityManagerFactory);
    
    
    public File selecionarImagem(ImageView imagem) throws IOException{
        Imagem img=new Imagem();
        this.file=img.selecionarImagem(imagem);
        return this.file;
    }
    
    public boolean validarCampos(List<TextInputControl> texts){
        
        boolean validacao=true;
        for (TextInputControl text : texts) {
            if(text.getText().isEmpty()){
                text.setStyle("-fx-border-color:red;-fx-border-style: segments(6.5);");
                validacao=false;
            }
            else
            {
                text.setStyle("-fx-border-color:transparent;");
            }
        }
        return validacao;
    }
    
        public List consultaAvancada(String tabela, String nome, String campo){
            List elementos=this.op.consultarLista("from "+tabela+" where "+campo+"='"+nome+"'");
            return elementos;
    }
    
        public List consultaBasica(String sql){
            this.entidade = sql.substring(0,1).toUpperCase().concat(sql.substring(1));
            this.elementos=this.op.consultarLista("select "+sql+" from "+entidade+" "+sql);;
            return elementos;
        
    }
        
    
    
        
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.op.setEntityManagerFactory(entityManagerFactory);
    }

    public Imagem getImagem() {
        return imagem;
    }
        
    
        
}
