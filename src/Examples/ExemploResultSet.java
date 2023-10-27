package Examples;

import connection.ConectaMySQL;
import entities.Aluno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploResultSet {
    public static void main(String[] args) {
        try {
            ConectaMySQL conexao = new ConectaMySQL();
            Connection cn = conexao.openDB();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Alunos");

            while (rs.next()) {
                int id = rs.getInt("idalunos");
                String nome = rs.getString("alunosNome");
                String telefone = rs.getString("alunosTelefone");
                double nota = rs.getDouble("alunosNota");

                Aluno novo = new Aluno(nome, telefone, nota);
                System.out.println(novo);
            }

            rs.close();
            st.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Falha ao realizar a operação");
            e.printStackTrace();
        }
    }
}
