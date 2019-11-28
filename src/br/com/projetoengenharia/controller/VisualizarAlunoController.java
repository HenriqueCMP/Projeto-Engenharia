/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.controller;

import br.com.projetoengenharia.dao.AlunoDAO;
import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.util.DateUtil;
import br.com.projetoengenharia.util.ManipulacaoPaginasUtil;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class VisualizarAlunoController implements Initializable {

    private Aluno alunoAtual = ManipulacaoPaginasUtil.getAlunoAtual();
    private AlunoDAO alunoDAO = new AlunoDAO();
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblCPF;

    @FXML
    private Label lblDataNascimento;

    @FXML
    private Label lblTelefone;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblRua;

    @FXML
    private Label lblBairro;

    @FXML
    private Label lblCEP;

    @FXML
    private Label lblNumero;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnDados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preencher();

        btnEditar.setOnMouseClicked((MouseEvent e) -> {
            //ManipulacaoPaginasUtil.setAlunoAtual(alunoAtual);
            ManipulacaoPaginasUtil.abreEditarAluno();
            ManipulacaoPaginasUtil.fecharVisualizarAluno();
        });
        btnEditar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                //ManipulacaoPaginasUtil.setAlunoAtual(alunoAtual);
                ManipulacaoPaginasUtil.abreEditarAluno();
                ManipulacaoPaginasUtil.fecharVisualizarAluno();
            }
        });

        btnDados.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreDados();
            ManipulacaoPaginasUtil.fecharVisualizarAluno();
        });
        btnDados.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreDados();
                ManipulacaoPaginasUtil.fecharVisualizarAluno();
            }
        });

        btnVoltar.setOnMouseClicked((MouseEvent e) -> {
            ManipulacaoPaginasUtil.abreTP();
            ManipulacaoPaginasUtil.fecharVisualizarAluno();
        });

        btnVoltar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ManipulacaoPaginasUtil.abreTP();
                ManipulacaoPaginasUtil.fecharVisualizarAluno();
            }
        });

        btnExcluir.setOnMouseClicked((MouseEvent e) -> {
            confirmarDeletar();
        });
        btnExcluir.setOnKeyPressed((KeyEvent e) -> {
            confirmarDeletar();
        });
    }

    private void preencher() {
        lblCPF.setText(alunoAtual.getCpf());
        lblNome.setText(alunoAtual.getNome());
        lblDataNascimento.setText(DateUtil.dateToString(alunoAtual.getDataNascimento()));
        lblTelefone.setText(alunoAtual.getContato().getTelefone());
        lblEmail.setText(alunoAtual.getContato().getEmail());
        lblRua.setText(alunoAtual.getEndereco().getRua());
        lblBairro.setText(alunoAtual.getEndereco().getBairro());
        lblCEP.setText(alunoAtual.getEndereco().getCep());
        lblNumero.setText(alunoAtual.getEndereco().getNumero());

    }

    private void confirmarDeletar() {
        if (JOptionPane.showConfirmDialog(null, "Deseja Excluir o Registro", "Confirmar a Exclusão", JOptionPane.YES_NO_OPTION) == 0) {
            try {
                alunoDAO.remover(alunoAtual);
                ManipulacaoPaginasUtil.abreTP();
                ManipulacaoPaginasUtil.fecharVisualizarAluno();
            } catch (Exception ex) {
                Logger.getLogger(VisualizarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
