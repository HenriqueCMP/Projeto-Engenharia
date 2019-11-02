/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.util;

import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.projeto.CadastrarAluno;
import br.com.projetoengenharia.projeto.EditarAluno;
import br.com.projetoengenharia.projeto.TelaPrincipal;
import br.com.projetoengenharia.projeto.VisualizarAluno;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

/**
 *
 * @author henri
 */
public class ManipulacaoPaginasUtil {
    
    private static TelaPrincipal tp = new TelaPrincipal();
    private static CadastrarAluno cadastrarAluno = new CadastrarAluno();
    private static VisualizarAluno visualizarAluno = new VisualizarAluno();
    private static EditarAluno editarAluno = new EditarAluno();
    
    public static Aluno alunoAtual = null;
    
    public static Aluno getAlunoAtual() {
        return alunoAtual;
    }

    public static void setAlunoAtual(Aluno aluno) {
        alunoAtual = aluno;
    }
    
    public static void abreTP() {
        try {
            tp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void fechaTP(){
        TelaPrincipal.getStage().close();
    }
    
    public static void abreCadastroAluno(){
        try {
            cadastrarAluno.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharCadastroAluno(){
        CadastrarAluno.getStage().close();
    }
    
    public static void abreVisualizarAluno(){
        try {
            visualizarAluno.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharVisualizarAluno(){
        VisualizarAluno.getStage().close();
    }
    
    public static void abreEditarAluno(){
        try {
            editarAluno.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharEditarAluno(){
        EditarAluno.getStage().close();
    }
}
