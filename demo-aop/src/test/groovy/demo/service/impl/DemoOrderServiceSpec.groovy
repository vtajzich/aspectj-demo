package demo.service.impl

import spock.lang.Specification

/**
 * Created by vtajzich
 */
class DemoOrderServiceSpec extends Specification {

    DemoOrderService service

    void setup() {
        service = new DemoOrderService()
    }

    def "PlaceOrder"() {

        when:
        
        service.placeOrder(null)
        
        then:
        
        true
    }
}
