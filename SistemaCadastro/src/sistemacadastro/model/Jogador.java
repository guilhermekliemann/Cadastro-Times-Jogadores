package sistemacadastro.model;

import java.util.ArrayList;

public class Jogador {
    
    private int id;
    private String nome;
    private String idade;
    private String altura;
    private String peso;
    private String posicao;
    private Time time;

    public Jogador() {
    }

    public Jogador(int id, String nome, String idade, String altura, String peso, String posicao, Time time) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
        this.posicao = posicao;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    static ArrayList<Jogador> listaJogadores = new ArrayList<>();
    
    public static void addJogadorLista(Jogador jogador) {
        listaJogadores.add(jogador);
    }
    
    public static ArrayList<Jogador> getListaJogadores() {
        return listaJogadores;
    }

     public int compareTo(Jogador jogador) {
        int result = this.nome.compareTo(jogador.nome);
        if (result == 0) {
            return 0;
        } else {
            return result;
        }
    }
    
}
