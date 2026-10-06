public class ArvoreAVL {

    private No raiz;

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    private No inserir(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else {
            no.direita = inserir(no.direita, valor);
        }

        return balancear(no);
    }

    public boolean remover(int valor) {
        if (!contem(valor)) {
            return false;
        }
        raiz = remover(raiz, valor);
        return true;
    }

    private No remover(No no, int valor) {
        if (no == null) {
            return null;
        }

        if (valor < no.valor) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = remover(no.direita, valor);
        } else {
            if (no.esquerda == null && no.direita == null) {
                System.out.println("   Caso 1 (folha): " + valor + " vira null");
                return null;
            }

            if (no.esquerda == null) {
                System.out.println("   Caso 2 (1 filho): " + no.direita.valor + " ocupa o lugar do " + valor);
                return no.direita;
            }
            if (no.direita == null) {
                System.out.println("   Caso 2 (1 filho): " + no.esquerda.valor + " ocupa o lugar do " + valor);
                return no.esquerda;
            }

            No predecessor = maior(no.esquerda);
            System.out.println("   Caso 3 (2 filhos): predecessor de " + valor + " é " + predecessor.valor
                    + ", o valor sobe e o nó predecessor original é apagado");
            no.valor = predecessor.valor;
            no.esquerda = removerMaior(no.esquerda);
        }

        return balancear(no);
    }

    private No removerMaior(No no) {
        if (no.direita == null) {
            return no.esquerda;
        }
        no.direita = removerMaior(no.direita);
        return balancear(no);
    }

    private No maior(No no) {
        while (no.direita != null) {
            no = no.direita;
        }
        return no;
    }

    public boolean contem(int valor) {
        No atual = raiz;
        while (atual != null) {
            if (valor == atual.valor) return true;
            atual = (valor < atual.valor) ? atual.esquerda : atual.direita;
        }
        return false;
    }

    private int altura(No no) {
        return no == null ? -1 : no.altura;
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    private int fator(No no) {
        return no == null ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private No balancear(No no) {
        atualizarAltura(no);
        int fb = fator(no);

        if (fb > 1) {
            if (fator(no.esquerda) >= 0) {
                System.out.println("   nó " + no.valor + " com fator " + fb
                        + " -> Esq-Esq: rotação simples à direita");
                return rotacaoDireita(no);
            }
            System.out.println("   nó " + no.valor + " com fator " + fb
                    + " -> Esq-Dir: rotação dupla (esquerda no filho, direita no nó)");
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (fb < -1) {
            if (fator(no.direita) <= 0) {
                System.out.println("   nó " + no.valor + " com fator " + fb
                        + " -> Dir-Dir: rotação simples à esquerda");
                return rotacaoEsquerda(no);
            }
            System.out.println("   nó " + no.valor + " com fator " + fb
                    + " -> Dir-Esq: rotação dupla (direita no filho, esquerda no nó)");
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No b = x.direita;

        x.direita = y;
        y.esquerda = b;

        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No b = y.esquerda;

        y.esquerda = x;
        x.direita = b;

        atualizarAltura(x);
        atualizarAltura(y);
        return y;
    }

    public void imprimir() {
        if (raiz == null) {
            System.out.println("   (árvore vazia)");
            return;
        }
        imprimir(raiz, 0);
    }

    private void imprimir(No no, int nivel) {
        if (no == null) return;
        imprimir(no.direita, nivel + 1);
        System.out.println("   " + "        ".repeat(nivel) + no.valor
                + " (h=" + no.altura + ", fb=" + fator(no) + ")");
        imprimir(no.esquerda, nivel + 1);
    }

    public void imprimirEmOrdem() {
        StringBuilder sb = new StringBuilder();
        emOrdem(raiz, sb);
        System.out.println("   em ordem: " + sb.toString().trim());
    }

    private void emOrdem(No no, StringBuilder sb) {
        if (no == null) return;
        emOrdem(no.esquerda, sb);
        sb.append(no.valor).append(' ');
        emOrdem(no.direita, sb);
    }

    public int alturaDaArvore() {
        return altura(raiz);
    }
}