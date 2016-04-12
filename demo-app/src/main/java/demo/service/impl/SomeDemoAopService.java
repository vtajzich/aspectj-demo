package demo.service.impl;

import demo.connector.SomeExternalSystemConnector;
import demo.service.SomeService;

import java.util.Collections;
import java.util.List;

/**
 * Created by vtajzich
 */
public class SomeDemoAopService implements SomeService {

    private SomeExternalSystemConnector externalSystemConnector;

    public SomeDemoAopService(SomeExternalSystemConnector externalSystemConnector) {
        this.externalSystemConnector = externalSystemConnector;
    }

    @Override
    public void save(Object anObject) {

        System.out.println("saved " + anObject);
    }

    @Override
    public void update(Object anObject) {

        System.out.println("updated " + anObject);
    }

    @Override
    public void delete(Object anObject) {

        System.out.println("deleted " + anObject);
    }

    @Override
    public Object getObjectByName(String name) {

        System.out.println("getObjectByName " + name);

        return "anObject " + name;
    }

    @Override
    public List<String> findAll() {

        List<String> result = Collections.emptyList();

        return result;
    }

    @Override
    public void callExternalSystem() {
        externalSystemConnector.askForRandomNumber();
    }
}
