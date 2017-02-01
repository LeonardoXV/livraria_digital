
package br.com.fatec.livrariadigital.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable {

    private List<Pedido> pedidos;
    private String emailUsuario;
    private String nome;
    private String sobreNome;
    private String senha;
    private Date dataNasc;
    private char sexo;
    private String cpf;
    private String endereco;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String tipo;

    public Usuario() {
    }

    @Id    
    @Column(name = "C_emailUsu")
    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    
    @Column(name = "C_nomeClie")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Column(name = "C_sobrNome")
    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
    
    @Column(name = "C_senhClie")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Column(name = "D_dataNasc")
    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    @Column(name = "C_sexoClie")
    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    @Column(name = "C_cpfClien")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @OneToMany(mappedBy = "usuarioInterno")
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    @Column(name = "N_endeClien")
    public String getEndereco() {
        return endereco;
    }

    public int getNumero() {
        return numero;
    }

    @Column(name = "C_bairClie")
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    @Column(name = "C_cidaClie")
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    @Column(name ="C_estaClie")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name = "C_cepClien")
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    @Column(name = "B_tipoUsua")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    

    public Usuario(String emailUsuario, String nome, String sobreNome, String senha, Date dataNasc, char sexo, String cpf, String endereco, int numero, String bairro, String cidade, String estado, String cep,String tipo) {
        this.emailUsuario = emailUsuario;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.tipo=tipo;
    }
    
    
    
    
}
