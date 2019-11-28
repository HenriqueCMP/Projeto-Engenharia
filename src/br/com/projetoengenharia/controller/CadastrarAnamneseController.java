/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.AnamneseDAO;
import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.model.Anamnese;
import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class CadastrarAnamneseController implements Initializable {

    private Anamnese anamnese;
    private Aluno aluno = ManipulacaoPaginasUtil.getAlunoAtual();
    private AnamneseDAO anamneseDAO = new AnamneseDAO();
    @FXML
    private TextField txtAtividade;

    @FXML
    private TextField txtDesconforto;

    @FXML
    private TextField txtObjeivo;

    @FXML
    private TextField txtProblemaCardiaco;

    @FXML
    private TextField txtDiabetes;

    @FXML
    private TextField txtMedicamentos;

    @FXML
    private TextField txtProblemaRespiratorio;

    @FXML
    private TextField txtProblemaOrtopedico;

    @FXML
    private TextField txtAlergia;

    @FXML
    private TextField txtDorColuna;

    @FXML
    private TextField txtOpiniao;

    @FXML
    private TextField txtNomeEmergencia;

    @FXML
    private TextField txtContatoEmergencia;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblAluno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblAluno.setText("Aluno: " + ManipulacaoPaginasUtil.getAlunoAtual().getNome());
        btnCadastrar.setOnMouseClicked((MouseEvent e) -> {
            if (!verificarVazio()) {
                inserirAnamnese();
            }
        });
        btnCadastrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (!verificarVazio()) {
                    inserirAnamnese();
                }
            }
        });
        btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreVisualizarAluno();
            ManipulacaoPaginasUtil.fecharCadastroAnamnese();
        });
        btnCancelar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreVisualizarAluno();
                ManipulacaoPaginasUtil.fecharCadastroAnamnese();
            }
        });
    }

    private void inserirAnamnese() {
        anamnese = new Anamnese();
        
        anamnese.setTempoAtividade(txtAtividade.getText());
        anamnese.setDesconforto(txtDesconforto.getText());
        anamnese.setObjetivos(txtObjeivo.getText());
        if(txtProblemaCardiaco.getText() == "Sim"
                || txtProblemaCardiaco.getText() == "sim"){
            anamnese.setProblemaCardiaco(true);
        }else{
            anamnese.setProblemaCardiaco(false);
        }
        if(txtDiabetes.getText() == "Sim"
                || txtDiabetes.getText() == "sim"){
            anamnese.setDiabetico(true);
        }else{
            anamnese.setDiabetico(false);
        }
        
        anamnese.setMedicamentos(txtMedicamentos.getText());
        
        if(txtProblemaRespiratorio.getText() == "Sim"
                || txtProblemaRespiratorio.getText() == "sim"){
            anamnese.setProblemaRespiratorio(true);
        }else{
            anamnese.setProblemaRespiratorio(false);
        }
        
        if(txtProblemaOrtopedico.getText() == "Sim"
                || txtProblemaOrtopedico.getText() == "sim"){
            anamnese.setProblemasOrtopedicos(true);
        }else{
            anamnese.setProblemasOrtopedicos(false);
        }
        anamnese.setAlergias(txtAlergia.getText());
        
        if(txtDorColuna.getText() == "Sim"
                || txtDorColuna.getText() == "sim"){
            anamnese.setDoresColuna(true);
        }else{
            anamnese.setDoresColuna(false);
        }
        anamnese.setOpiniaoMedica(txtOpiniao.getText());
        anamnese.setNomeContato(txtNomeEmergencia.getText());
        anamnese.setNumeroContato(txtContatoEmergencia.getText());
        
        anamnese.setAluno(aluno);
        try {
            anamneseDAO.inserir(anamnese);
            ManipulacaoPaginasUtil.abreVisualizarAluno();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exito");
            alert.setHeaderText("Cadastro Concluído");
            alert.setContentText("Anamnese Cadastrada com Sucesso");
            alert.show();
            ManipulacaoPaginasUtil.fecharCadastroAnamnese();
        } catch (Exception ex) {
            Logger.getLogger(CadastrarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

        private boolean verificarVazio() {
        if (txtAtividade.getText().isEmpty()
                || txtDesconforto.getText().isEmpty()
                || txtObjeivo.getText().isEmpty()
                || txtProblemaCardiaco.getText().isEmpty()
                || txtDiabetes.getText().isEmpty()
                || txtMedicamentos.getText().isEmpty()
                || txtProblemaRespiratorio.getText().isEmpty()
                || txtProblemaOrtopedico.getText().isEmpty()
                || txtAlergia.getText().isEmpty()
                || txtDorColuna.getText().isEmpty()
                || txtOpiniao.getText().isEmpty()
                || txtNomeEmergencia.getText().isEmpty()
                || txtContatoEmergencia.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cadastro Invalido");
            alert.setContentText("Campo(s) Obrigatório(s) em Branco");
            alert.show();
            return true;
        }
        return false;
    }
    

}
