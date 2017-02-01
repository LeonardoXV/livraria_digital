
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.aplicacao.OperacoesUsuarioInterno;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.Usuario;
import java.util.Date;
import java.util.List;
import javafx.scene.control.TextInputControl;
import javax.swing.JOptionPane;


public class UsuarioController extends ControllerGeral {
    OperacoesUsuarioInterno ed=new OperacoesUsuarioInterno(this.entityManagerFactory);
    public void cadastrar(String email,String nome,String sobreNome,String senha,Date dataNasc,char sexo,String cpf,String endereco,int numero,String bairro,String cidade,String estado,String cep, String tipo){
       Usuario usuario=new Usuario(email, nome, sobreNome, senha, dataNasc, sexo, cpf, endereco, numero, bairro, cidade, estado, cep,tipo);
       this.ed.cadastrarNoBanco(usuario);
    }
    
    public void deletar(String email){
        List<Usuario>usuarios=this.consultaAvancada("Usuario",email,"emailUsuario");
        this.ed.deletar(usuarios.get(0));
    }
    public void atualizar(Usuario usuario,String email,String nome,String sobreNome,String senha,Date dataNasc,char sexo,String cpf,String endereco,int numero,String bairro,String cidade,String estado,String cep){
     
        usuario.setBairro(bairro);
        usuario.setCep(cep);
        usuario.setCidade(cidade);
        usuario.setCpf(cpf);
        usuario.setDataNasc(dataNasc);
        usuario.setEndereco(endereco);
        usuario.setEstado(estado);
        usuario.setNome(nome);
        usuario.setNumero(numero);
        usuario.setSenha(senha);
        usuario.setSexo(sexo);
        usuario.setSobreNome(sobreNome);
        
        this.ed.atualizar(usuario);
    }

    @Override
    public boolean validarCampos(List<TextInputControl> texts) {
        System.out.println("sadasjhdhaskjdhajskh");
        List<Usuario> usuarios=this.consultaBasica("Usuario");
        boolean validacao=true;
            for (Usuario usuario : usuarios) {
                if(usuario.getEmailUsuario().equals(texts.get(0).getText())){
                     JOptionPane.showMessageDialog(null, "USUARIO JÁ CADASTRADO");
                    validacao=false;
                }
                
            }
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
    
    public boolean validarCamposAtualizar(List<TextInputControl> texts){
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

}
