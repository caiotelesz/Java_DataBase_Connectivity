package Examples;

import connection.ConectaMySQL;
import entities.Aluno;

import javax.swing.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

// Fazer uma consulta pelo nome ou sobrenome do aluno
public class ExConsultaParteNome{
    public static void main(String[] args) throws Exception{
        List<Aluno> alunos = new ExConsultaParteNome()
                .consultar(JOptionPane.showInputDialog(
                        "Digite o nome do aluno a ser consultado:"));
        System.out.println("Data e hora da consulta: " + lerHora());
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }}
    public List<Aluno> consultar(String nome) throws Exception {
        String queryCmd = "select * from alunos where "
                + "alunosNome like ? ";
        List<Aluno> alunos = new ArrayList<Aluno>();
        Connection con = null;
        try {
            con = ConectaMySQL.openDB();
            PreparedStatement ps1 = con.prepareStatement(queryCmd);
            ps1.setString(1, (nome != null ? '%'+nome.trim()+'%' : ""));
            ResultSet rs = ps1.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("idalunos");
                String nomeAluno = rs.getString("alunosNome");
                double nota = rs.getDouble("alunosNota");
                String telefone = rs.getString("alunosTelefone");
                Aluno novo = new Aluno(id, nomeAluno, telefone, nota);
                alunos.add(novo);
            }
        } catch (SQLException e) {
            throw new Exception(e); // encapsula excecao original
        } finally {
            ConectaMySQL.closeDB();
        }
        return alunos;
    }
    public static String lerHora() {
        DateFormat dia = new SimpleDateFormat("dd/MM/YYYY");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dia.format(date) + " - " + dateFormat.format(date);
    }
}