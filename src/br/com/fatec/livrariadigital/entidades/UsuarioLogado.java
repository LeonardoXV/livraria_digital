/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "tb_usuariologado")
public class UsuarioLogado implements Serializable{
    private int idLogado;
    private Usuario usuario;
    
    @Id
    public int getIdLogado() {
        return idLogado;
    }

    public void setIdLogado(int idLogado) {
        this.idLogado = idLogado;
    }
    
    @OneToOne
    @JoinColumn(name = "C_emailUsu", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioLogado() {
    }

    public UsuarioLogado(int idLogado, Usuario usuario) {
        this.idLogado = idLogado;
        this.usuario = usuario;
    }
    
    
    
    
    
}
