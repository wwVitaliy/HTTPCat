package org.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import static org.example.Utils.*;

public class HttpStatusChecker {

    public String getStatusImage(int code) throws Exception {
        String catLink = "";
        int responseCode = 0;

        if (Pattern.matches(CODE_REGEX, String.valueOf(code))) {
            catLink = CAT_WEBSITE + "/" + code + EXTENSION;
            URL catURL = new URL(catLink);

            HttpURLConnection connection = (HttpURLConnection) catURL.openConnection();
            responseCode = connection.getResponseCode();
        }
        if (responseCode == 200) {
            return catLink;
        } else {
            throw new Exception(WRONG_CODE_MESSAGE + code);
        }
    }

}
