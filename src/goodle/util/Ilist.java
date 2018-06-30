package goodle.util;

/**
 *
 * @author Daniel Alves e Gabriela dos Santos. Código baseado nos slides do
 * professor João Batista
 */
public interface Ilist {

    /**
     * Método para adicionar no início da lista
     *
     * @param data - objeto que irá ser adicionado
     */
    public void addFirst(Object data);

    /**
     * Método para adicionar no final da lista
     *
     * @param data - objeto que irá ser adicionado
     */
    public void addLast(Object data);

    /**
     * Método para remover no início da lista
     *
     * @return objeto removido
     */
    public Object removeFirst();

    /**
     * Método para remover no final da lista
     *
     * @return objeto removido
     */
    public Object removeLast();

    /**
     * Método para saber o tamanho da lista
     *
     * @return o tamanho da lista
     */
    public int size();

    /**
     * Método para saber se a lista está vazia
     *
     * @return true caso a lista esteja vazia
     */
    public boolean isEmpty();

    /**
     * Método do iterator
     *
     * @return o iterator da lista
     */
    public Iterator iterator();

    /**
     * Retorna o conteúdo do nó do index recebido
     *
     * @param index
     * @return n.getData();
     */
    public Object get(int index);

    /**
     * Troca o conteúdo do nó do index recebido pelo dado recebido
     *
     * @param index
     * @param data
     */
    public void set(int index, Object data);

}
