package Examples;

import connection.ConectaMySQL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExemploPreparedStatement {
    public static void main(String[] args) {
        try {
            ConectaMySQL conexao = new ConectaMySQL();
            Connection cn = conexao.openDB();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Alunos (alunosNome,  alunosTelefone, alunosNota)"
            + "VALUES (?, ?, ?)");

            ps.setString(1, JOptionPane.showInputDialog("Nome: ")); // digitar o nome
            ps.setString(2, JOptionPane.showInputDialog("Telefone: ")); //digitar o telefone
            ps.setDouble(3, Double.parseDouble(JOptionPane.showInputDialog("Nota: "))); // digitar a nota

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");

            ps.close();
            cn.close();

            System.out.println("Conexão encerrada");
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a operação.");
            e.printStackTrace();
        }
    }
}
