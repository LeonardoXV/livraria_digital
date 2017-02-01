/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import java.util.List;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author r2d2
 */
public interface TelaGeral {
    List<TextInputControl> preencheTexts();
    void limpar();
}
