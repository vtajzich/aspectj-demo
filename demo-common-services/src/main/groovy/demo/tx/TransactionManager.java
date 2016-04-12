package demo.tx;

/**
 * Created by vtajzich
 */
public interface TransactionManager {

    void begin();

    void commit();

    void rollback();
}
