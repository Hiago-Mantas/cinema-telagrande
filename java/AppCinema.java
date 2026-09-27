public class AppCinema {
    public static void main(String[] args) {
        // 1) AGREGAÇÃO — os assentos existem na sala; a sessão só os agrupa
        Assento f11 = new Assento('F', 11);
        Assento f12 = new Assento('F', 12);
        Assento f13 = new Assento('F', 13);
        Sessao sessao20h = new Sessao("Duna: Parte Dois", "20:00");
        sessao20h.adicionarAssento(f11);
        sessao20h.adicionarAssento(f12);
        sessao20h.adicionarAssento(f13);
        System.out.println("Sessão '" + sessao20h.getFilme() + "' às " + sessao20h.getHorario()
                + " com " + sessao20h.getAssentos().size() + " assento(s).");

        // 2) COMPOSIÇÃO — a Venda cria os seus ingressos
        Venda venda1 = new Venda();
        Ingresso inteira = venda1.adicionarInteira(sessao20h, f11, 30.0);
        Ingresso meia    = venda1.adicionarMeia(sessao20h, f12, 30.0, "Carteirinha estudante 2026");

        // 3) HERANÇA + POLIMORFISMO — a mesma pergunta, resposta diferente por tipo
        System.out.println("\nIngresso " + inteira.getAssento() + " (inteira): R$ " + inteira.calcularPreco());
        System.out.println("Ingresso " + meia.getAssento() + " (meia):    R$ " + meia.calcularPreco());
        System.out.println("Total da venda: R$ " + venda1.calcularTotal());

        // 4) INVARIANTE — vender o mesmo assento de novo na mesma sessão deve ser recusado
        Venda venda2 = new Venda();
        try {
            venda2.adicionarInteira(sessao20h, f11, 30.0);
        } catch (IllegalStateException e) {
            System.out.println("\nRecusado (invariante): " + e.getMessage());
        }

        // 5) ENCAPSULAMENTO — preço negativo é recusado na entrada
        try {
            venda2.adicionarInteira(sessao20h, f13, -10.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (encapsulamento): " + e.getMessage());
        }

        // 6) ESTADO DA VENDA — só anda por operações
        venda1.pagar();
        System.out.println("\nVenda 1: " + venda1.getStatus());
        try {
            venda1.cancelar();
        } catch (IllegalStateException e) {
            System.out.println("Recusado (estado): " + e.getMessage());
        }

        // 7) CANCELAMENTO — ingressos somem junto e o assento volta a ficar livre
        venda2.adicionarInteira(sessao20h, f13, 30.0);
        System.out.println("\nF13 livre antes de cancelar? " + sessao20h.estaLivre(f13));
        venda2.cancelar();
        System.out.println("Venda 2: " + venda2.getStatus() + " (" + venda2.getIngressos().size() + " ingresso(s))");
        System.out.println("F13 livre depois de cancelar? " + sessao20h.estaLivre(f13));
    }
}
