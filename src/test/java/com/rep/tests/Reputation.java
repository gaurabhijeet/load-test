package com.rep.tests;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Reputation extends Simulation {

    private final HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://service.reputation.com/locator/f348913d-9a1a-4182-b0b9-0b60fd78096b");

    private final ChainBuilder dallas = exec(http("Dallas")
            .get("/entities?address=32.7766642%7C-96.79698789999999%7C100000&isAPP=APP%7CAMG%7CDREY%7CSILV&searchBy=address"));
    private final ChainBuilder chicago = exec(http("Chicago")
            .get("/entities?address=41.8781136%7C-87.6297982%7C100000&isAPP=APP%7CAMG%7CDREY%7CSILV&searchBy=address"));
    private final ChainBuilder washington = exec(http("Washington ")
            .get("/entities?address=38.9059849%7C-77.03341790000002%7C100000&isAPP=APP%7CAMG%7CDREY%7CSILV&searchBy=address"));

    private final ScenarioBuilder scenario = scenario("Get entities f348913d-9a1a-4182-b0b9-0b60fd78096b")
            .exec(dallas, chicago,washington);
    {
        setUp(
                scenario.injectOpen(
                        nothingFor(2),
                        rampUsers(20).during(10)
                )
        ).protocols(httpProtocol);
    }


}
