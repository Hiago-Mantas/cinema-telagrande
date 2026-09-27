public class IngressoMeia extends Ingresso {
    private final String comprovante;

    public IngressoMeia(Sessao sessao, Assento assento, double preco, String comprovante) {
        super(sessao, assento, preco);
        if (comprovante == null || comprovante.isBlank()) {
            sessao.liberarAssento(assento);   // desfaz a venda do assento feita pelo super(...)
            throw new IllegalArgumentException("Meia-entrada exige comprovação.");
        }
        this.comprovante = comprovante;
    }

    public String getComprovante() { return comprovante; }

    @Override
    public double calcularPreco() { return getPreco() / 2; }   // metade do preço
}
