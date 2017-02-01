
package br.com.fatec.livrariadigital.entidades;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;

@Entity
@Table(name = "tb_editoras")
public class Editora implements Serializable {
    
    
    private int IdEditora;
    private String cnpj;
    private String endereco;
    private String telefone;
    private List<Livro>livros;
    private String nomeFantasia;
    private int numero;
    private String bairro;
    private String razaoSocial;
    private String ddd;
    private String cidade;
    private String cep;
    private String estado;

    
    
    
    public Editora() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_idEditor")
    public int getIdEditora() {
        return IdEditora;
    }
    
    
    public void setIdEditora(int IdEditora) {
        this.IdEditora = IdEditora;
    }
    @Column(name = "C_nomeFant")
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    
    @Column(name="N_numeEnde")
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    @Column(name = "C_bairEnde")
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    @Column(name = "C_razaSoci")
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    
    @Column(name = "N_cnpjEdit")
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    

    @Column(name="C_endeEdit")
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String Endereco) {
        this.endereco = Endereco;
    }
    
    @Column(name = "C_teleEdit")
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @OneToMany
    @JoinColumn(name = "N_idEditor")
    public List<Livro> getLivros() {
        return livros;
    }
    
    
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
    @Column(name = "C_DDD")
    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
    
    @Column(name = "C_cidaEdit")
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    @Column(name = "C_cepEdito")
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    @Column(name = "C_estaEdit")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
    

    public Editora(String cnpj, String endereco, String telefone,  String nomeFantasia, int numero, String bairro, String razaoSocial, String ddd, String cidade,String cep,String estado) {
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.livros = livros;
        this.nomeFantasia = nomeFantasia;
        this.numero = numero;
        this.bairro = bairro;
        this.razaoSocial = razaoSocial;
        this.ddd = ddd;
        this.cidade=cidade;
        this.cep=cep;
        this.estado=estado;
    }
    
    
    
    
    
}