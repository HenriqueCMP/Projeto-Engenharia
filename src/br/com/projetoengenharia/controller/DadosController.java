/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class DadosController implements Initializable {

     @FXML
    private Label lblAluno;

    @FXML
    private TableView<?> tableAnamnese;

    @FXML
    private TableColumn<?, ?> clnDataAnamnese;

    @FXML
    private TableView<?> tableAntropometria;

    @FXML
    private TableColumn<?, ?> clnDataAntropometria;

    @FXML
    private Button btnCadastrarAnamnese;

    @FXML
    private Button btnVisualizarAnamnese;

    @FXML
    private Button btnDeletarAnamnese;

    @FXML
    private Button btnCadastrarAntropometria;

    @FXML
    private Button btnVisualizarAntropometria;

    @FXML
    private Button btnDeletarAntropometria;
    
    @FXML
    private Button btnVoltar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCadastrarAnamnese.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreCadastroAnamnese();
            ManipulacaoPaginasUtil.fechaDados();
        });
        btnCadastrarAnamnese.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreCadastroAnamnese();
                ManipulacaoPaginasUtil.fechaDados();
            }
        });
        btnVoltar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreVisualizarAluno();
            ManipulacaoPaginasUtil.fechaDados();
        });
        btnVoltar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreVisualizarAluno();
                ManipulacaoPaginasUtil.fechaDados();
            }
        });
    }    
    
}
