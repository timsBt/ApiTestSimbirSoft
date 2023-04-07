import Models.Ability;
import Models.LimitPokemons;
import Models.NamesPokemons;
import Models.Weight;
import io.qameta.allure.Step;

import java.util.List;

import static io.restassured.RestAssured.given;


public class Steps {

    @Step ("Получение ограниченного списка покемонов")
    public static List<LimitPokemons> limitPokemonsList(int count) {
        return given()
                .when()
                .get("?limit=" + count + "&offset=0")
                .then()
                .extract().jsonPath().getList("results", LimitPokemons.class);
    }

    @Step ("Получение Models.Ability (умение) покемона")
    public static List<Ability> ability(String name) {
        return given()
                .when()
                .get(name)
                .then()
                .extract().jsonPath().getList("abilities.ability", Ability.class);
    }

    @Step ("Получение Models.Weight (вес) покемона")
    public static Weight checkWeightPokemonsTests(String name) {
        return given()
                .when()
                .get(name)
                .then()
                .extract().as(Weight.class);
    }

    @Step ("Получение Name (имя) покемона")
    public static NamesPokemons checkNamePokemonsTest (String name){
        return given()
                .when()
                .get(name)
                .then()
                .extract().as(NamesPokemons.class);
    }
}