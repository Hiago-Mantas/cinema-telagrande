public class Assento {
    private final char fila;
    private final int numero;

    public Assento(char fila, int numero) {
        if (!Character.isLetter(fila))
            throw new IllegalArgumentException("Fila deve ser uma letra: " + fila);
        if (numero <= 0)
            throw new IllegalArgumentException("Número do assento deve ser positivo: " + numero);
        this.fila = Character.toUpperCase(fila);
        this.numero = numero;
    }

    public char getFila()  { return fila; }
    public int getNumero() { return numero; }

    @Override
    public String toString() { return "" + fila + numero; }   // ex.: F12
}
