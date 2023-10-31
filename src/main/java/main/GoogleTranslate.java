package main;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.Scanner;

public class GoogleTranslate {
    public String translateText(String input) throws Exception {

        // sl: nguồn ngôn ngữ
        // tl: ngôn ngữ đích
        // q: văn bản cần dịch
        String url = String.format(
                "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s",
                "en", "vi", java.net.URLEncoder.encode(input, "UTF-8"));

        HttpClient httpClient = HttpClients.createDefault();


        try {
            HttpGet request = new HttpGet(url);
            String result = EntityUtils.toString(httpClient.execute(request).getEntity());
            JSONParser parser = new JSONParser();
            JSONArray jsonData = (JSONArray) parser.parse(result);
            JSONArray translationItems = (JSONArray) jsonData.get(0);
            StringBuilder translation = new StringBuilder();

            for (Object item : translationItems) {
                JSONArray translationLineObject = (JSONArray) item;
                String translationLineString = (String) translationLineObject.get(0);
                translation.append(" ").append(translationLineString);
            }

            if (translation.length() > 1) {
                translation.deleteCharAt(0);
            }

            return translation.toString();


        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        GoogleTranslate translator = new GoogleTranslate();
        String input = scanner.nextLine();
        String translation = translator.translateText(input);
        System.out.println(translation);
    }
}
