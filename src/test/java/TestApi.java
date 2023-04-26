import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import java.util.List;
import static io.restassured.RestAssured.given;

public class TestApi extends Spetifications {

  private final static String URL = "https://pokeapi.co/";

    @Test
    @Description ("Проверка имени покемона rattata")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkNameRattataTest (){
        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        given()
                .when()
                .get("api/v2/pokemon/rattata")
                .then()
                .body("name", Matchers.equalTo("rattata"));
    }

    @Test
    @Description ("Проверка имени покемона pidgeotto")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkNamePidgeottoTest (){
        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        given()
                .when()
                .get("api/v2/pokemon/pidgeotto")
                .then()
                .body("name",Matchers.equalTo("pidgeotto"));
    }

    @Test
    @Description ("Проверка что покемон rattata имеет умение run-away")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkAbilityRattataTest () {
        List <Ability> abilities = Steps.ability("rattata");
        List <String> rattataAbility = abilities.stream().map(Ability::getName).toList();
        for (String i:rattataAbility) {
            Assertions.assertTrue(rattataAbility.contains("run-away"));
        }
    }

    @Test
    @Description ("Проверка что покемон pidgeotto не имеет умение run-away")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkAbilityPidgeottoTest () {
        List <Ability> abilities = Steps.ability("pidgeotto");
        List <String> pidgeottoAbility = abilities.stream().map(Ability::getName).toList();
        for (String i:pidgeottoAbility) {
            Assertions.assertFalse(pidgeottoAbility.contains("run-away"));
        }
    }

    @Test
    @Description ("Проверка что вес rattata меньше веса pidgeotto")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkWeightPokemonsTest (){
        int rattataWeight = Steps.checkWeightPokemonsTests("rattata").getWeight();
        int pidgeottoWeight = Steps.checkWeightPokemonsTests("pidgeotto").getWeight();
        Assertions.assertTrue(rattataWeight < pidgeottoWeight);
    }

    @Test
    @Description ("Проверка что покемонов в ограниченном списке равно 20")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkLimitTest(){
        List <LimitPokemons> results = Steps.limitPokemonsList();
        Assertions.assertEquals(20, results.size());
    }

    @Test
    @Description ("Проверка что у покемонов в ограниченном списке есть name")
    @Execution(ExecutionMode.CONCURRENT)
    public void limitNameTest(){
        List <LimitPokemons> pokemonsName = Steps.limitPokemonsList();
        pokemonsName.forEach(x-> Assertions.assertTrue(x.getResultNames().contains(x.getResultNames())));
    }
}
