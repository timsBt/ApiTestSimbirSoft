import java.util.List;

import static io.restassured.RestAssured.given;

public class Steps {
    private final static String URL = "https://pokeapi.co/";

    public static List <LimitPoke> pokesName() {
        Spetifications.installSpetification(Spetifications.requestSpec(URL), Spetifications.responseSpecOK200());
        return given()
                .when()
                .get("api/v2/pokemon?limit=100000&offset=0")
                .then()
                .extract().body().jsonPath().getList("results", LimitPoke.class);
    }

}
