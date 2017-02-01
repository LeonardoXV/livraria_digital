/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.aplicacao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author leovieira
 */
public class Imagem {
        
       private BufferedImage bufferedImage;
       private File fileImg;
       
 
       
       public Image converterFile(File file) throws IOException{
           this.bufferedImage=ImageIO.read(file.getAbsoluteFile());
           Image im=SwingFXUtils.toFXImage(this.bufferedImage, null);
           
           return im;
       }
       
       
        public File selecionarImagem(ImageView imagem) throws IOException{
            Stage fileChooserStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecione a Imagem Desejada");
            this.fileImg= fileChooser.showOpenDialog(fileChooserStage);
            imagem.setImage(converterFile(this.fileImg));
            return this.fileImg;

        }  
    }
       

