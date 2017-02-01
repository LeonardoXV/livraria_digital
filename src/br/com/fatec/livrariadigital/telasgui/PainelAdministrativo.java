/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.telasgui.controllers.PainelAdministrativoController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PainelAdministrativo extends Application implements Initializable {

    @FXML private Label labelLivros,labelEditoras,labelPedidos,labelUsuarios;
     private PainelAdministrativoController controller=new PainelAdministrativoController ();
    @Override
    public void start(Stage primaryStage) throws Exception {
       Pane root= FXMLLoader.load(getClass().getResource("fxml/PainelAdministrativo.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelasCadastrados.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Cadastro de Autores");
       primaryStage.setFullScreen(true);
       primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Integer>dados=this.controller.obterNumeroDeEditoras();
        this.labelEditoras.setText(Integer.toString(dados.get(0)));
        this.labelUsuarios.setText(Integer.toString(dados.get(1)));
        this.labelLivros.setText(Integer.toString(dados.get(2)));
        this.labelPedidos.setText(Integer.toString(dados.get(3)));
    }

   
}
