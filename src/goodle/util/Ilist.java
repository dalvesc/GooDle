package goodle.util;

/**
 *
 * @author Daniel Alves e Gabriela dos Santos. Adaptado do código do professor João Batista.
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
     *  Retorna o conteúdo do nó do index recebido.
     * 
     * @param index índice do nó que será buscado.
     * @return n.getData();
     */
    public Object get(int index);
    
    /**
     * Troca o conteúdo do nó do index recebido pelo dado recebido
     * 
     * @param index índice do nó que terá seu conteúdo modificado.
     * @param data novo conteúdo do nó.
     */
    public void set(int index, Object data);
    
    /**
     * Verifica se a lista em questão contém um determinado objeto.
     * 
     * @param data objeto a ser procurado na lista.
     * @return "true" se o objeto estiver contido na lista e "false" se não.
     */
    public boolean contains(Object data);
    
    /**
     * Remove da lista determinado objeto.
     * 
     * @param data objeto a ser removido.
     */
    public void remove(Object data);
    
    /**
     * Remove da lista o nó com o índice indicado.
     * 
     * @param index índice do nó a ser removido.
     */
    public void remove(int index);

}
