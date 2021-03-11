package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20202)
                .when().get("/student/{id}");

        //Cihat abiyle bu kodu yazdik sebebi ise Postmandeki gibi Arrayli sonuc vermesi icin:))
         response.prettyPrint();

        //verify status code
        assertEquals(response.statusCode(), 200);


        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();


        //get values from jsonpath
        String firsName = jsonPath.getString("students.firstName[0]");
        System.out.println("firsName = " + firsName);

        String lastName = jsonPath.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phone = jsonPath.get("students.contact[0].phone");
        System.out.println("phone = " + phone);


        //get me city and zipcode, do assertion
        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);
        assertEquals(city,"Chicago");

        String zipCode = jsonPath.getString("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);
        assertEquals(zipCode,"60606");

        String firstname2 = jsonPath.getString("students.firstName");
        System.out.println("firstname2 = " + firstname2);

        //        String firstname3 =response.path("students.firstName");
//        System.out.println("firstname3 = " + firstname3);

       // String zipCode2= response.path("students.company[0].address.zipCode");
       // System.out.println("zipCode2 = " + zipCode2);




    }
    //BU TESTI CIHAT ABIYLE BIRLIKTE KURDUK

        @Test
        public void testWithMB () {
            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("id", 20202)
                    .when().get("/students/{id}");

            response.prettyPrint();

            //verify status code
            assertEquals(response.statusCode(), 200);


            //assign response to jsonpath
            JsonPath jsonPath = response.jsonPath();


            //get values from jsonpath
            String firsName = jsonPath.getString("firstName");
            System.out.println("firsName = " + firsName);

            String lastName = jsonPath.getString("lastName");
            System.out.println("lastName = " + lastName);


        }
    }