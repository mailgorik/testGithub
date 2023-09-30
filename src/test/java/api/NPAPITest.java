package api;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NPAPITest extends BaseApiTest {
    String API_key = "1cc3732e48f039dd2c2e58be8f13ce9e";
    private Map<String, Object> reqBody = new HashMap<>();

    @BeforeEach
    public void setReqBody(){
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("CityName", "Львів");
        methodProperties.put("Limit", "5");
        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Address");
        reqBody.put("calledMethod", "searchSettlements");
        reqBody.put("methodProperties", methodProperties);
    }

    @Test
    public void verifyThatSuccess() {
        given()
                .spec(requestSpecification)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("data[0].Addresses[2].Present", equalTo("с. Львівське, Карлівський р-н, Полтавська обл."));

    }
}

