package Examples;

import connection.ConectaMySQL;
import entities.Aluno;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Consulta pelo nome completo do aluno registrado no Banco de dados
public class ExConsultaNomeCompleto {
    static Connection con = null;

    public static void main(String[] args) throws Exception {
        Aluno aluno = new ExConsultaNomeCompleto().consultarNomeCompleto(
                JOptionPane.showInputDialog("Digite o nome completo do aluno a ser consultado: "));
        System.out.println("Data e hora da consulta: " + lerHora());
        System.out.println(aluno);
    }

    static Aluno consultarNomeCompleto(String nome) throws Exception {
        Aluno novo = null;
        String querycmd = "select * from alunos where " + "alunosNome like ? ";

        try {
            con = ConectaMySQL.openDB();
            PreparedStatement ps1 = con.prepareStatement(querycmd);
            ps1.setString(1, (nome != null ? nome.trim() : ""));
            ResultSet rs = ps1.executeQuery();
            while(rs.next()) {
                String nomeAluno = rs.getString("alunosNome");
                double nota = rs.getDouble("alunosNota");
                String telefone = rs.getString("alunosTelefone");
                novo = new Aluno(nomeAluno, telefone, nota);
            }
        } catch (SQLException e) {
            throw new Exception(e); // encapsula exceção original
        } finally {
            ConectaMySQL.closeDB();
        }
        return novo;
    }
    public static String lerHora() {
        DateFormat dia = new SimpleDateFormat("dd/MM/YYYY");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        Date date = new Date();

        return dia.format(date) + " - " + dateFormat.format(date);
    }
}
