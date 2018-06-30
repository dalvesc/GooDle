package goodle.util;

/**
 * Adaptado do código https://github.com/douglasrz/ArvoreAVL Adaptado por Daniel
 * Alves e Gabriela Nunes
 *
 * @author Douglas, Daniel Alves e Gabriela dos Santos
 */
public interface AVL {

    /**
     * Método para inserir um novo item na árvore
     *
     * @param palavra
     */
    public void inserir(Object palavra);

    /**
     * Método para remover determinado item da árvore
     *
     * @param palavra palavra que deseja remover da árvore
     */
    public void remove(Object palavra);

    /**
     * Método para buscar determinada palavra na árvore
     *
     * @param palavra palavra que deseja buscar
     * @return retorna a palavra que foi buscada
     */
    public Object busca(Object palavra);
}