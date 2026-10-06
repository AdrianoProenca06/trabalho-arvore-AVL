public class Main {

    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        int[] insercoes = {55, 26, 29, 13, 12, 11, 16, 1, 5, 29, -15, 4, 16, 8, 4, 5, 3, 1312, 100, 88};
        int[] remocoes = {4, 29, 100, 5, -15, 16, 55};

        System.out.println("===== INSERÇÕES =====");
        for (int i = 0; i < insercoes.length; i++) {
            int v = insercoes[i];
            System.out.println("\n" + (i + 1) + ") inserindo " + v);
            arvore.inserir(v);
            arvore.imprimir();
        }

        System.out.println("\n\n===== ÁRVORE APÓS TODAS AS INSERÇÕES =====");
        arvore.imprimir();
        arvore.imprimirEmOrdem();

        System.out.println("\n\n===== REMOÇÕES =====");
        for (int i = 0; i < remocoes.length; i++) {
            int v = remocoes[i];
            System.out.println("\n" + (i + 1) + ") removendo " + v);
            if (!arvore.remover(v)) {
                System.out.println("   valor não encontrado");
            }
            arvore.imprimir();
        }

        System.out.println("\n\n===== ÁRVORE FINAL =====");
        arvore.imprimir();
        arvore.imprimirEmOrdem();
        System.out.println("   altura da árvore (nó raiz): " + arvore.alturaDaArvore());
    }
}