import java.util.ArrayList;
import java.util.List;

public class Sessao {
    private final String filme;
    private final String horario;
    private final List<Assento> assentos = new ArrayList<>();          // AGREGAÇÃO
    private final List<Assento> assentosVendidos = new ArrayList<>();

    public Sessao(String filme, String horario) {
        if (filme == null || filme.isBlank())
            throw new IllegalArgumentException("Filme é obrigatório.");
        if (horario == null || horario.isBlank())
            throw new IllegalArgumentException("Horário é obrigatório.");
        this.filme = filme;
        this.horario = horario;
    }

    public void adicionarAssento(Assento assento) {   // recebe pronto (não cria)
        if (assento != null && !assentos.contains(assento)) assentos.add(assento);
    }

    public boolean estaLivre(Assento assento) {
        return assentos.contains(assento) && !assentosVendidos.contains(assento);
    }

    public void venderAssento(Assento assento) {
        // PRÉ-CONDIÇÃO: o assento é desta sessão e ainda está livre
        if (!assentos.contains(assento))
            throw new IllegalArgumentException("Assento " + assento + " não pertence a esta sessão.");
        if (assentosVendidos.contains(assento))
            throw new IllegalStateException("Assento " + assento + " já vendido nesta sessão.");
        assentosVendidos.add(assento);   // PÓS-CONDIÇÃO: agora está vendido (mantém o INVARIANTE)
    }

    public void liberarAssento(Assento assento) {
        // PRÉ-CONDIÇÃO: só libera o que foi vendido
        if (!assentosVendidos.contains(assento))
            throw new IllegalStateException("Assento " + assento + " não está vendido.");
        assentosVendidos.remove(assento);   // PÓS-CONDIÇÃO: volta a ficar livre
    }

    public String getFilme()   { return filme; }
    public String getHorario() { return horario; }
    public List<Assento> getAssentos() { return List.copyOf(assentos); }
}
