
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;

public class FirstTest {

    @Test
    public void searchBoxTest() {
        Response response = given().param("url", "search-alias=aps").param("field-keywords", "HRS")
                .when().get("https://cc.healthrecoverysolutions.com/login").then().contentType(ContentType.HTML).extract()
                .response();
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(htmlPath.getString("html.head.title"), "HRS | ClinicianConnect");
    }
}