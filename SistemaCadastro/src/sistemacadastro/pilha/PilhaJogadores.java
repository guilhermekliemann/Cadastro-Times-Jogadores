package sistemacadastro.pilha;

public class PilhaJogadores<Jogador> {

    private Jogador[] pilha;
    private int posicaoTopo;

    public int getPosicaoTopo() {
        return posicaoTopo;
    }

    public void setPosicaoTopo(int posicaoTopo) {
        this.posicaoTopo = posicaoTopo;
    }

    public PilhaJogadores(int tamanho) {
        pilha = (Jogador[]) new Object[tamanho];
        posicaoTopo = -1;
    }

    public void push(Jogador e) {
        pilha[++posicaoTopo] = e;
    }

    public Jogador pop() {
        return pilha[posicaoTopo--];
    }

    public Jogador top() {
        return pilha[posicaoTopo];
    }

    public boolean isEmpty() {
        return posicaoTopo == -1;
    }

    public boolean isFull() {
        return posicaoTopo == pilha.length - 1;
    }
    
}
