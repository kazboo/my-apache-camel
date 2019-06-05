package my.apache.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:start?period=3s")
            .to("sql:SELECT * FROM T_ORDER ?dataSourceRef=ds_mssql")
            .to("log:end?showAll=true&multiline=true");
    }

}
