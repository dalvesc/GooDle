package goodle.util;

/**
 *
 * @author Daniel Alves e Gabriela dos Santos. Código basedo nos slides do
 * professor João Batista
 */
public interface Iterator {

    /**
     * Método para saber se tem um próximo elemento na lista
     *
     * @return true caso tenha elemento
     */
    public boolean hasNext();

    /**
     * Método para retornar o elemento da lista
     *
     * @return elemento da lista
     */
    public Object next();

}
