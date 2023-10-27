package Examples;

import connection.ConectaMySQL;
import entities.Aluno;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class ExemploAlteracao {
    public static void main(String[] args) {
        try {
            realizaOperacao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void realizaOperacao() throws Exception {
        Connection cn = ConectaMySQL.openDB();
        Statement st = null;
        try {
            st = cn.createStatement();
            String nome = JOptionPane.showInputDialog(
                    "Digite o nome do aluno a ser alterado");
            ExConsultaNomeCompleto consulta = new ExConsultaNomeCompleto();
            Aluno alterado = consulta.consultarNomeCompleto(nome);
            if (consulta != null) {
                int resp = JOptionPane.showConfirmDialog(null,
                        "Confirma a alteração do aluno: \n" + alterado);
                if (resp == 0) {
                    alterarAluno(alterado);
                } else {
                    JOptionPane.showMessageDialog(null, "O aluno: "
                            + alterado.getNome() + " NÃO foi alterado");
                }
            } else
                JOptionPane.showMessageDialog(null, "O aluno: "
                        + alterado.getNome() + " NÃO foi encontrado");
        } catch (SQLException e) {
            throw new Exception("Falha ao acessar base de dados.\n"
                    + e.getMessage());
        } finally {
            st.close();
            cn.close();
        }
    }

    public static Aluno alterarAluno(Aluno alterado) throws SQLException {
        Connection con = ConectaMySQL.openDB();
        PreparedStatement pstm = con.prepareStatement("UPDATE alunos SET "
                + " alunosNome=?, alunosTelefone=?, alunosNota=? WHERE idalunos=?");
        String nome = JOptionPane.showInputDialog("Digite o novo Nome: ");
        String telefone = JOptionPane.showInputDialog("Digite o telefone: ");
        double nota = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota: "));
        pstm.setString(1, nome);
        pstm.setString(2, telefone);
        pstm.setDouble(3, nota);
        pstm.setInt(4, alterado.getId());
        Aluno aposAlteracao = new Aluno(alterado.getId(), nome, telefone, nota);
        pstm.execute();
        JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!!!");
        return aposAlteracao;
    }
}