package my.apache.camel;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class MyRouteBuilderTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MyRouteBuilder();
    }

    @Test
    public void test() throws Exception {
        context().createProducerTemplate().sendBody("direct:execTest", "test");
    }
}
