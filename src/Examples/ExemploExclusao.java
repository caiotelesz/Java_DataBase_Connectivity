package Examples;

import connection.ConectaBanco;
import entities.Aluno;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploExclusao {
    public static void main(String[] args) {
        try {
            realizaOperacao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void realizaOperacao() throws Exception{
        Connection cn = null;
        Statement st = null;

        try {
            cn = ConectaBanco.getConexao();
            st = cn.createStatement();
            String nome = JOptionPane.showInputDialog("Digite o nome do aluno a ser excluido");
            ExConsultaNomeCompleto consulta = new ExConsultaNomeCompleto();
            Aluno excluido = consulta.consultarNomeCompleto(nome);
            int resp = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do aluno: \n" + excluido);

            if(resp == 0) {
                st.executeUpdate("DELETE FROM Alunos WHERE idalunos='"
                        + excluido.getId() + "'");
                JOptionPane.showMessageDialog(null, "O aluno " +
                    excluido.getNome() + " foi excluído com sucesso!!!");
            } else {
                JOptionPane.showMessageDialog(null, "O aluno " +
                    excluido.getNome() + " não foi excluído");
            }
        } catch (SQLException e) {
            throw new Exception("Falha ao acessar base de dados. \n" + e.getMessage());
        } finally {
            st.close();
            cn.close();
        }
    }
}
