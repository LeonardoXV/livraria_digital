package br.com.fatec.livrariadigital.telasgui.controllers;
import br.com.fatec.livrariadigital.aplicacao.OperacoesEditora;
import br.com.fatec.livrariadigital.entidades.Editora;
import java.util.List;

public class EditorasController extends ControllerGeral{
    
    OperacoesEditora ed=new OperacoesEditora(this.op.getEntityManagerFactory());
    

    public EditorasController() {}
    
    public void cadastrarEditora(String cnpj,String endereco,String telefone,String nomeFantasia,int numero,String bairro,String razao, String ddd,String cidade,String cep,String estado){
      
        Editora editora=new Editora(cnpj, endereco, telefone, nomeFantasia, numero, bairro, razao, ddd, cidade,cep,estado);
        this.ed.cadastrarNoBanco(editora);
    }
    
     public void deletar(String cnpj){
        List<Editora>editora=this.consultaAvancada("Editora",cnpj,"cnpj");
        this.ed.deletar(editora.get(0));
    }
     
     public void atualizarEditora(Editora editora,String cnpj,String endereco,String telefone,String nomeFantasia,int numero,String bairro,String razao, String ddd,String cidade,String cep,String estado){
         editora.setBairro(bairro);
         editora.setCep(cep);
         editora.setCidade(cidade);
         editora.setCnpj(cnpj);
         editora.setEndereco(endereco);
         editora.setNomeFantasia(nomeFantasia);
         editora.setNumero(numero);
         editora.setRazaoSocial(razao);
         editora.setTelefone(telefone);
         editora.setDdd(ddd);
         editora.setEstado(estado);
         this.ed.atualizar(editora);
     }
}
