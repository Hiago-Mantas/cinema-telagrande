import java.util.ArrayList;
import java.util.List;

public class Venda {
    private final List<Ingresso> ingressos = new ArrayList<>();   // COMPOSIÇÃO
    private StatusVenda status;

    public Venda() {
        this.status = StatusVenda.ABERTA;
    }

    public Ingresso adicionarInteira(Sessao sessao, Assento assento, double preco) {
        verificarAberta();
        Ingresso i = new IngressoInteira(sessao, assento, preco);   // a parte nasce dentro do todo
        ingressos.add(i);
        return i;
    }

    public Ingresso adicionarMeia(Sessao sessao, Assento assento, double preco, String comprovante) {
        verificarAberta();
        Ingresso i = new IngressoMeia(sessao, assento, preco, comprovante);
        ingressos.add(i);
        return i;
    }

    public double calcularTotal() {
        double total = 0;
        for (Ingresso i : ingressos) total += i.calcularPreco();   // MENSAGEM polimórfica
        return total;
    }

    public void pagar() {   // ABERTA -> PAGA
        verificarAberta();
        if (ingressos.isEmpty()) throw new IllegalStateException("Venda sem ingressos não pode ser paga.");
        status = StatusVenda.PAGA;
    }

    public void cancelar() {   // ABERTA -> CANCELADA
        verificarAberta();
        for (Ingresso i : ingressos) i.getSessao().liberarAssento(i.getAssento());   // MENSAGEM
        ingressos.clear();   // composição: os ingressos somem junto com a venda
        status = StatusVenda.CANCELADA;
    }

    private void verificarAberta() {
        if (status != StatusVenda.ABERTA)
            throw new IllegalStateException("Operação não permitida: venda está " + status + ".");
    }

    public StatusVenda getStatus() { return status; }
    public List<Ingresso> getIngressos() { return List.copyOf(ingressos); }
}
