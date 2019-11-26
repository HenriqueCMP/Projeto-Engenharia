/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.projetoengenharia.dao.AlunoDAO;
import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.util.DateUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henri
 */
public class AlunoTest {
    
    private static AlunoDAO alunoDAO;
    public AlunoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        alunoDAO = new AlunoDAO();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private void excluir() throws Exception{
        Aluno aluno = alunoDAO.getPorCPF("000000");
        alunoDAO.remover(aluno);
    }
    
    private void inserir() throws Exception{
        Aluno aluno = new Aluno();
        aluno.setCpf("000000");
        aluno.setNome("aluno");
        aluno.setDataNascimento(DateUtil.stringToDate("19/11/2019"));
        aluno.getContato().setTelefone("00000000");
        aluno.getContato().setEmail("teste@teste.com");
        aluno.getEndereco().setRua("Rua teste");
        aluno.getEndereco().setNumero("000");
        aluno.getEndereco().setBairro("teste");
        aluno.getEndereco().setCep("58000-000");
        
        alunoDAO.inserir(aluno);
    }
    
    @Test
    public void testeInserir() throws Exception {
        // quantidade de alunos antes de salvar
        int quantidadeAnterior = alunoDAO.listar().size();
        // salva um aluno
        inserir();
        // quantidade de alunos depois de salvar
        int quantidadeAtual = alunoDAO.listar().size();
        // a quantidade atual de alunos deve igual a quantidade anterior + 1
        assertEquals(quantidadeAnterior + 1, quantidadeAtual);
        // apagar o registro
        excluir();
    }
    
    
    
}
