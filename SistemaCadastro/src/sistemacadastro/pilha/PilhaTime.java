package sistemacadastro.pilha;

public class PilhaTime<Time> {
    
    private Time[] pilha;
    private int posicaoTopo;

    public int getPosicaoTopo() {
        return posicaoTopo;
    }

    public void setPosicaoTopo(int posicaoTopo) {
        this.posicaoTopo = posicaoTopo;
    }

    public PilhaTime(int tamanho) {
        pilha = (Time[]) new Object[tamanho];
        posicaoTopo = -1;
    }

    public void push(Time e) {
        pilha[++posicaoTopo] = e;
    }

    public Time pop() {
        return pilha[posicaoTopo--];
    }

    public Time top() {
        return pilha[posicaoTopo];
    }

    public boolean isEmpty() {
        return posicaoTopo == -1;
    }

    public boolean isFull() {
        return posicaoTopo == pilha.length - 1;
    }
    
}
