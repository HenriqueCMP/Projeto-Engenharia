/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.AlunoDAO;
import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class TelaPrincipalController implements Initializable {

    private List<Aluno> alunos = new ArrayList<>();
    private AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno alunoAtual;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVisualizar;

    @FXML
    private TextField txtCPF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ManipulacaoPaginasUtil.setAlunoAtual(null);

        try {
            alunos = alunoDAO.listarAlunos();
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnCadastrar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreCadastroAluno();
            ManipulacaoPaginasUtil.fechaTP();
        });
        btnCadastrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreCadastroAluno();
                ManipulacaoPaginasUtil.fechaTP();
            }
        });

        btnVisualizar.setOnMouseClicked((MouseEvent e) -> {
            try {
                verificarCPF();
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnVisualizar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    verificarCPF();
                } catch (Exception ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void verificarCPF() throws Exception {
        if (!verificarAlunoExistente()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Aluno Invalido");
            alert.setContentText("CPF Incorreto");
            alert.show();
        } else {

            if (alunoDAO.getPorCPF(txtCPF.getText()) == null) {
                System.out.println("erro");
            } else {
                ManipulacaoPaginasUtil mpu = new ManipulacaoPaginasUtil();
                alunoAtual = alunoDAO.getPorCPF(txtCPF.getText());
                mpu.setAlunoAtual(alunoAtual);
                ManipulacaoPaginasUtil.abreVisualizarAluno();
                ManipulacaoPaginasUtil.fechaTP();
            }

        }
    }

    private boolean verificarAlunoExistente() {

        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(txtCPF.getText())) {
                return true;
            }
        }
        return false;
    }

}
