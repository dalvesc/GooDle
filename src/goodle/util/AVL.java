package goodle.util;

/**
 * Adaptado do código https://github.com/douglasrz/ArvoreAVL 
 * Adaptado por Daniel Alves e Gabriela dos Santos
 *
 * @author Douglas
 */
public interface AVL {

    public int size();
    
    /**
     * Método para inserir um novo item na árvore 
     * @param palavra palavra que irá ser adicionada
     * @param pagina pagina que irá ser adicionada
     */
    public void inserir(Object palavra, Object pagina);

    /**
     * Método para remover determinado item da árvore
     * @param palavra palavra que deseja remover da árvore
     */
    public void remove(Object palavra);

    /**
     * Método para buscar determinada palavra na árvore
     * @param palavra palavra que deseja buscar
     * @return retorna a palavra que foi buscada
     */
    public Object busca(Object palavra);
}
