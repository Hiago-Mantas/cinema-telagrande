public abstract class Ingresso {
    private final Sessao sessao;     // associação
    private final Assento assento;   // associação
    private final double preco;      // dado protegido: não existe setPreco()

    protected Ingresso(Sessao sessao, Assento assento, double preco) {
        if (sessao == null || assento == null)
            throw new IllegalArgumentException("Ingresso precisa de sessão e assento.");
        if (preco < 0)
            throw new IllegalArgumentException("Preço não pode ser negativo: " + preco);
        sessao.venderAssento(assento);   // MENSAGEM (a regra do assento é validada lá dentro)
        this.sessao = sessao;
        this.assento = assento;
        this.preco = preco;
    }

    public double getPreco()    { return preco; }
    public Sessao getSessao()   { return sessao; }
    public Assento getAssento() { return assento; }

    public abstract double calcularPreco();   // polimórfico
}
