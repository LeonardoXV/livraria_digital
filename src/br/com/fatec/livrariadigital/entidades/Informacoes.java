
package br.com.fatec.livrariadigital.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_informacoes")
public class Informacoes {
    private int numEditoras;
    private int numUsuarios;
    private int id;
    private int numLivros;
    private int numPedidos;
    
    @Column(name="N_numEdito")
    public int getNumEditoras() {
        return numEditoras;
    }

    public void setNumEditoras(int numEditoras) {
        this.numEditoras = numEditoras;
    }
    
    @Column(name ="N_numUsuar")
    public int getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }
    
    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "N_numLivros")
    public int getNumLivros() {
        return numLivros;
    }

    public void setNumLivros(int numLivros) {
        this.numLivros = numLivros;
    }
    
    @Column(name = "N_numPedid")
    public int getNumPedidos() {
        return numPedidos;
    }

    public void setNumPedidos(int numPedidos) {
        this.numPedidos = numPedidos;
    }
    
    
    
    
    
}
