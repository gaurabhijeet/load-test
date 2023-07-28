package com.rep.tests;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Velocity extends Simulation {

    private final HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://locator-api.localsearchprofiles.com/api/LocationSearchResults/?configuration=f348913d-9a1a-4182-b0b9-0b60fd78096b");

    private final ChainBuilder dallas = exec(http("Dallas")
            .get("&&address=32.7766642%2C-96.79698789999999&isAPP=APP%7CAMG%7CDREY%7CSILV&searchby=address"));
    private final ChainBuilder chicago = exec(http("Chicago")
            .get("&&address=41.8781136%2C-87.6297982&isAPP=APP%7CAMG%7CDREY%7CSILV&searchby=address"));
    private final ChainBuilder washington = exec(http("Washington ")
            .get("&&address=38.9059849%2C-77.03341790000002&isAPP=APP%7CAMG%7CDREY%7CSILV&searchby=address"));

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
