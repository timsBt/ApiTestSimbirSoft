import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import java.util.List;

public class TestApi extends Spetifications {

    @Test
    @Description ("Проверка имени покемона rattata")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkNameRattataTest () {
        String rattataName = Steps.checkNamePokemonsTest("rattata").getPokemonsName();
        Assertions.assertEquals("rattata", rattataName, "Имя покемона не соответсвует");
    }

    @Test
    @Description ("Проверка имени покемона pidgeotto")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkNamePidgeottoTest (){
        String pidgeottoName = Steps.checkNamePokemonsTest("pidgeotto").getPokemonsName();
        Assertions.assertEquals("pidgeotto", pidgeottoName, "Имя покемона не соответсвует");
    }

    @Test
    @Description ("Проверка что у покемона rattata есть умение run-away")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkAbilityRattataTest () {
        List <Ability> abilities = Steps.ability("rattata");
        List <String> rattataAbility = abilities.stream().map(Ability::getName).toList();
        for (String i:rattataAbility) {
            Assertions.assertTrue(rattataAbility.contains("run-away"));
        }
    }

    @Test
    @Description ("Проверка что у покемона pidgeotto нет умения run-away")
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
        Assertions.assertEquals( 20,results.size());
    }

    @Test
    @Description ("Проверка что у покемонов в ограниченном списке есть name")
    @Execution(ExecutionMode.CONCURRENT)
    public void limitNameTest(){
        List <LimitPokemons> pokemonsName = Steps.limitPokemonsList();
        pokemonsName.forEach(x-> Assertions.assertTrue(x.getResultNames().contains(x.getResultNames())));
    }
}
