package entities;

public class Aluno {
    String nome, telefone;
    int id;
    double nota;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
    public Aluno(String nome, String telefone, double nota) {
        super();
        this.nome = nome;
        this.telefone = telefone;
        this.nota = nota;
    }
    public Aluno(int id, String nome, String telefone, double nota) {
        super();
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", "
                + "telefone=" + telefone + ", nota=" + nota + "]";
    }


}
