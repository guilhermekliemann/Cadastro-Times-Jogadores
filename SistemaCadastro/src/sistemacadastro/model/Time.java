package sistemacadastro.model;

import java.util.ArrayList;

public class Time {
    
    private int id;
    private String nome;
    private String tecnico;
    private String estadio;

    public Time() {
    }

    public Time(int id, String nome, String tecnico, String estadio) {
        this.id = id;
        this.nome = nome;
        this.tecnico = tecnico;
        this.estadio = estadio;
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

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
    
    static ArrayList<Time> listaTimes = new ArrayList<>();
    
    public static void addTimeLista(Time time) {
        listaTimes.add(time);
    }
    
    public static ArrayList<Time> getListaTimes() {
        return listaTimes;
    }

    public int compareTo(Time time) {
        int result = this.nome.compareTo(time.nome);
        if (result == 0) {
            return 0;
        } else {
            return result;
        }
    }
    
}
