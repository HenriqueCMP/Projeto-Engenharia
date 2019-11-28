/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.projeto;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author henri
 */
public class CadastrarAluno extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarAluno.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Carrega FXML
        Parent root = FXMLLoader.load(getClass().getResource("/br/com/projetoengenharia/view/CadastrarAluno.fxml"));
        // Add o FXML em uma Cena
        Scene scene = new Scene(root);
        //Add titula na janela
        stage.setTitle("Boa Forma: Cadastrar Aluno");
        //Add a cena em uma janela;
        stage.setScene(scene);
        //Abre a janela
        stage.show();
        setStage(stage);
        stage.setResizable(false);
        stage.setOnCloseRequest((WindowEvent arg0) -> {
            System.exit(0);
        });
    }

}
