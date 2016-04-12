package demo.service;

import java.util.List;

/**
 * Created by vtajzich
 */
public interface SomeService {

    void save(Object anObject);

    void update(Object anObject);

    void delete(Object anObject);

    void callExternalSystem();

    Object getObjectByName(String name);

    List<String> findAll();
}
