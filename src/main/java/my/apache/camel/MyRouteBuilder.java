package my.apache.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.exec.ExecResult;
import org.apache.commons.io.IOUtils;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("direct:sqlTest")
//        .to("sql:SELECT * FROM T_ORDER ?dataSourceRef=ds_mssql")
//        .to("log:end?showAll=true&multiline=true");

        from("direct:execTest")
        .to("exec:cmd?args=/c echo HUGA")
        .log("${body}")
        .to("exec:cmd?args=RAW(/c dir && dir)")
        .convertBodyTo(String.class, "Shift-JIS")
        .log("${body}")
        .to("exec:cmd?args=/c java -version")
        .log("${body}")
        .to("exec:cmd?args=/c mkdir test&useStderrOnEmptyStdout=true")
//        .convertBodyTo(String.class, "Shift-JIS")
//        .log("${body}")
        .process(exchange -> {
            ExecResult result = (ExecResult) exchange.getIn().getBody();
//            String wordCountOutput = exchange.getIn().getBody(String.class);
//            System.out.println("wo:" + wordCountOutput);
            if (result.getExitValue() == 0) {
                System.out.println(IOUtils.toString(result.getStdout(), "Shift-JIS"));
            } else {
                System.err.println(IOUtils.toString(result.getStderr(), "Shift-JIS"));
                throw new RuntimeException("failed to make directory");
            }
        });
    }

}
