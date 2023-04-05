import io.qameta.allure.Step;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Steps {

    private final static String URL = "https://pokeapi.co/";

    @Step ("Получение ограниченного списка покемонов")
    public static List<LimitPokemons> limitPokemonsList() {
        Spetifications.installSpetification(Spetifications.requestSpec(URL), Spetifications.responseSpecOK200());
        return given()
                .when()
                .get("api/v2/pokemon")
                .then()
                .extract().jsonPath().getList("results", LimitPokemons.class);
    }

    @Step ("Получение Ability (умение) покемона")
    public static List<Ability> ability(String name) {
        Spetifications.installSpetification(Spetifications.requestSpec(URL), Spetifications.responseSpecOK200());
        return given()
                .when()
                .get("api/v2/pokemon/" + name)
                .then()
                .extract().jsonPath().getList("abilities.ability", Ability.class);
    }

    @Step ("Получение Weight (вес) покемона")
    public static Weight checkWeightPokemonsTests(String name) {
        Spetifications.installSpetification(Spetifications.requestSpec(URL), Spetifications.responseSpecOK200());
        return given()
                .when()
                .get("api/v2/pokemon/" + name)
                .then()
                .extract().as(Weight.class);
    }

    public static NamesPokemons checkNamePokemonsTest (String name){
        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        return given()
                .when()
                .get("api/v2/pokemon/"+ name)
                .then()
                .extract().as(NamesPokemons.class);
    }
}