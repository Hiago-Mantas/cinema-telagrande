public class IngressoInteira extends Ingresso {
    public IngressoInteira(Sessao sessao, Assento assento, double preco) {
        super(sessao, assento, preco);
    }

    @Override
    public double calcularPreco() { return getPreco(); }   // preço inteiro
}
