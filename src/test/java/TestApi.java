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
    public void checkAbilityRattataTest (){
        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        given()
                .when()
                .get("api/v2/pokemon/rattata")
                .then()
                .body("abilities[0].ability.name",Matchers.equalTo("run-away"));
    }

    @Test
    @Description ("Проверка что вес rattata меньше веса pidgeotto")
    @Execution(ExecutionMode.CONCURRENT)
    public void checkWeightRattataPidgeottoTest (){
        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        int ratta = given()
                .when()
                .get("api/v2/pokemon/rattata")
                .then()
                .extract().body().jsonPath().getInt("weight");

        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        int pidge = given()
                .when()
                .get("api/v2/pokemon/pidgeotto")
                .then()
                .extract().body().jsonPath().getInt("weight");
        Assertions.assertTrue(ratta < pidge);
    }

 /*   @Test
    @Description ("Проверка что покемонов в ограниченном списке равно количеству покемонов в count")
    @Execution(ExecutionMode.CONCURRENT)
    public void checklimitTest(){
        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        List <LimitPoke> results = given()
                .when()
                .get("api/v2/pokemon?limit=100000&offset=0")
                .then()
                .extract().body().jsonPath().getList("results", LimitPoke.class);
       int count = given()
                .when()
                .get("api/v2/pokemon?limit=100000&offset=0")
                .then()
                .extract().body().jsonPath().getInt("count");
        Assertions.assertTrue(results.size() == count);
        System.out.println(results.size() + " " + count);
    }*/


/*   @Test
   @Description ("Проверка что у покемонов в ограниченном списке есть name")
   @Execution(ExecutionMode.CONCURRENT)
   public void limitNameTest(){
        Spetifications.installSpetification(Spetifications.requestSpec(URL),Spetifications.responseSpecOK200());
        List <LimitPoke> pokesName = given()
                .when()
                .get("api/v2/pokemon?limit=100000&offset=0")
                .then()
                .extract().body().jsonPath().getList("results", LimitPoke.class);
        pokesName.forEach(x-> Assertions.assertTrue(x.getResultNames().contains(x.getResultNames())));
    }*/


   @Test
   @Description ("Проверка что у покемонов в ограниченном списке есть name")
   @Execution(ExecutionMode.CONCURRENT)
   public void limitNameTest2(){
        List <LimitPoke> pokesName = Steps.pokesName();
        pokesName.forEach(x-> Assertions.assertTrue(x.getResultNames().contains(x.getResultNames())));
    }












}
