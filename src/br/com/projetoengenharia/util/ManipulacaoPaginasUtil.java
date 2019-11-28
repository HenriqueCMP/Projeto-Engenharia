/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.util;

import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.model.Funcionario;
import br.com.projetoengenharia.projeto.CadastrarAluno;
import br.com.projetoengenharia.projeto.CadastrarAnamnese;
import br.com.projetoengenharia.projeto.CadastrarFuncionario;
import br.com.projetoengenharia.projeto.Dados;
import br.com.projetoengenharia.projeto.EditarAluno;
import br.com.projetoengenharia.projeto.EditarFuncionario;
import br.com.projetoengenharia.projeto.Login;
import br.com.projetoengenharia.projeto.TelaPrincipal;
import br.com.projetoengenharia.projeto.VisualizarAluno;
import br.com.projetoengenharia.projeto.VisualizarFuncionario;
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
    private static Login login = new Login();
    private static CadastrarFuncionario cadastrarFuncionario = new CadastrarFuncionario();
    private static VisualizarFuncionario visualizarFuncionario = new VisualizarFuncionario();
    private static EditarFuncionario editarFuncionario = new EditarFuncionario();
    private static Dados dados = new Dados();
    private static CadastrarAnamnese cadastrarAnamnese = new CadastrarAnamnese();
//    private static Alunos alunos = new Alunos();
    
    public static Aluno alunoAtual = null;
    public static Funcionario funcionarioAtual = null;
    public static Funcionario funcionarioVisualizar = null;
    
    public static Funcionario getFuncionarioAtual(){
        return funcionarioAtual;
    }
    public static void setFuncionarioAtual(Funcionario funcionario){
        funcionarioAtual = funcionario;
    }
    
    public static Funcionario getFuncionarioVisualizar(){
        return funcionarioVisualizar;
    }
    public static void setFuncionarioVisualizar(Funcionario funcionario){
        funcionarioVisualizar = funcionario;
    }
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
    
    public static void abreDados(){
        try {
            dados.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fechaDados(){
        Dados.getStage().close();
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
    
    public static void abreCadastroAnamnese(){
        try {
            cadastrarAnamnese.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharCadastroAnamnese(){
        CadastrarAnamnese.getStage().close();
    }
    
    public static void abreCadastroFuncionario(){
        try {
            cadastrarFuncionario.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharCadastroFuncionario(){
        CadastrarFuncionario.getStage().close();
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
    
    public static void abreVisualizarFuncionario(){
        try {
            visualizarFuncionario.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharVisualizarFuncionario(){
        VisualizarFuncionario.getStage().close();
    }
    public static void abreLogin(){
        try{
            login.start(new Stage());
        }catch (Exception ex){
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharLogin(){
        Login.getStage().close();
    }
    
//    public static void abreAlunos(){
//        try {
//            alunos.start(new Stage());
//        } catch (Exception ex) {
//            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public static void fecharAlunos(){
//        Alunos.getStage().close();
//    }
    
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
    
    public static void abreEditarFuncionario(){
        try {
            editarFuncionario.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ManipulacaoPaginasUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharEditarFuncionario(){
        EditarFuncionario.getStage().close();
    }
}
