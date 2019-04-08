package task;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 *
 * @param <T> t
 */
public interface Task<T> {

    /**
     * run the task.
     *
     * @return null
     */
    T run();
}
