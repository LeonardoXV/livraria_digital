
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.aplicacao.OperacoesAutor;

import br.com.fatec.livrariadigital.entidades.Autor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AutoresController extends ControllerGeral {
   

    OperacoesAutor ed=new OperacoesAutor(this.getEntityManagerFactory());
    
    public void cadastrarAutor(String nome, String dataNasce, String dataFalec, String localNasc,String localMorte, String bibiografia) {
        Date nascimento=new Date(dataNasce);
        Date falecimento=new Date(dataFalec);
        Autor autor=new Autor(nome,nascimento, falecimento, localNasc, localMorte, bibiografia);
        this.ed.cadastrarNoBanco(autor);
    }
    
    public void cadastrarAutor(String nome, String dataNasce, String localNasc, String bibiografia) {
        Date nascimento=new Date(dataNasce);
        Autor autor=new Autor(nome, nascimento, null, localNasc,null, bibiografia);
        this.ed.cadastrarNoBanco(autor);
    }
    
    public void deletar(String nome){
        List<Autor>autores=this.consultaAvancada("Autor",nome,"nome");
        this.ed.deletar(autores.get(0));
    }
    
    public void atualizarAutor(Autor autor,String nome,String dataNasce,String dataFale,String localNasc,String localMorte,String biografia){
       if(dataFale.isEmpty()){
        autor.setBiografia(biografia);
        autor.setDataFale(null);
        autor.setDataNasce(new Date(dataNasce));
        autor.setLocalMorte(null);
        autor.setLocalNasc(localNasc);
        this.ed.atualizar(autor);
       }else{
        autor.setBiografia(biografia);
        autor.setDataFale(new Date(dataFale));
        autor.setDataNasce(new Date(dataNasce));
        autor.setLocalMorte(localMorte);
        autor.setLocalNasc(localNasc);
        this.ed.atualizar(autor);
       }
        
    }

     
}