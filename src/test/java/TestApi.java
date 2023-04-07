import Models.Ability;
import Models.LimitPokemons;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestApi extends Spetifications {

    @BeforeEach
    public void runSpetification(){
        spetificationRun();
    }

    @Test
    @DisplayName("TC-1 Проверка имени (name) покемона rattata")
    @Description ("Проверка имени покемона rattata")
    public void checkNameRattataTest () {
        String rattataName = Steps.checkNamePokemonsTest("rattata").getPokemonsName();
        Assertions.assertEquals("rattata", rattataName, "Имя покемона не соответсвует");
    }

    @Test
    @DisplayName("ТС-2 Проверка покемона pidgeotto")
    @Description ("Проверка имени покемона pidgeotto")
    public void checkNamePidgeottoTest (){
        String pidgeottoName = Steps.checkNamePokemonsTest("pidgeotto").getPokemonsName();
        Assertions.assertEquals("pidgeotto", pidgeottoName, "Имя покемона не соответсвует");
    }

    @Test
    @DisplayName("ТС-3 Проверка умения (ability) покемона rattata")
    @Description ("Проверка что у покемона rattata есть умение run-away")
    public void checkAbilityRattataTest () {
        List <Ability> abilities = Steps.ability("rattata");
        List <String> rattataAbility = abilities.stream().map(Ability::getName).toList();
        for (String i:rattataAbility) {
            Assertions.assertTrue(rattataAbility.contains("run-away"));
        }
    }

    @Test
    @DisplayName("ТС-4 Проверка умения (ability) покемона pidgeotto")
    @Description ("Проверка что у покемона pidgeotto нет умения run-away")
    public void checkAbilityPidgeottoTest () {
        List <Ability> abilities = Steps.ability("pidgeotto");
        List <String> pidgeottoAbility = abilities.stream().map(Ability::getName).toList();
        for (String i:pidgeottoAbility) {
            Assertions.assertFalse(pidgeottoAbility.contains("run-away"));
        }
    }

    @Test
    @DisplayName("ТС-5 Сравнение веса покемона rattata и pidgeotto")
    @Description ("Проверка что вес rattata меньше веса pidgeotto")
    public void checkWeightPokemonsTest (){
        int rattataWeight = Steps.checkWeightPokemonsTests("rattata").getWeight();
        int pidgeottoWeight = Steps.checkWeightPokemonsTests("pidgeotto").getWeight();
        Assertions.assertTrue(rattataWeight < pidgeottoWeight);
    }

    @Test
    @DisplayName("ТС-6 Проверка ограничения списка  покемонов")
    @Description ("Проверка что покемонов в ограниченном списке равно 30")
    public void checkLimitTest(){
        List <LimitPokemons> results = Steps.limitPokemonsList(10);
        Assertions.assertEquals( 10,results.size());
    }

    @Test
    @DisplayName("ТС-7 Проверка что у каждого покемона в ограниченном списке есть имя (name)")
    @Description ("Проверка что у покемонов в ограниченном списке есть name")
    public void limitNameTest(){
        List <LimitPokemons> pokemonsName = Steps.limitPokemonsList(10);
        List <String> pokemons = pokemonsName.stream().map(LimitPokemons::getResultNames).toList();
        for (String name:pokemons) {
            Assertions.assertNotNull(name);
        }
    }
}
