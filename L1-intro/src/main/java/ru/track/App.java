package ru.track;

/**
 * TASK:
 * POST request to  https://guarded-mesa-31536.herokuapp.com/track
 * fields: name,github,email
 *
 * LIB: http://unirest.io/java.html
 *
 *
 */
public class App {

    public static final String URL = "http://guarded-mesa-31536.herokuapp.com/track";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_GITHUB = "github";
    public static final String FIELD_EMAIL = "email";

    public static void main(String[] args) throws Exception {
       // 1) Use Unirest.post()
        // 2) Get response .asJson()
        // 3) Get json body and JsonObject
        // 4) Get field "success" from JsonObject


	HttpResponse <JsonNode> r = Unirest.post(URL)
                .field (name,"Evgenia")
                .field (github, "https://github.com/EvgeniaKomleva")
                .field (email, "komleva.1999@inbox.ru")
                .asJson();
        System.out.println( r.getBody().getObject().get("success"));
    }
    }

}
