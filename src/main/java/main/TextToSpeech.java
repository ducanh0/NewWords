package main;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TextToSpeech {
    private final String API_KEY = "AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw";
    private static final String GOOGLE_SYNTHESISER_URL = "https://www.google.com/speech-api/v2/synthesize?enc=mpeg" + "&client=chromium";
    private String languageSpeech = "en_US";

    public void setLanguageSpeech(String languageSpeech) {
        this.languageSpeech = languageSpeech;
    }

    public InputStream getMP3(String text) throws IOException {
        String languageSpeech = this.languageSpeech;

        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);

        StringBuilder urlBuilder = new StringBuilder(GOOGLE_SYNTHESISER_URL);
        urlBuilder.append("&key=" + API_KEY);
        urlBuilder.append("&text=").append(encodedText);
        urlBuilder.append("&lang=").append(languageSpeech);

        URL url = new URL(urlBuilder.toString());

        URLConnection urlConnection = url.openConnection();

        urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0");

        return urlConnection.getInputStream();
    }

    public void speakText(String text) {
        Thread playbackThread = new Thread(() -> {
            try {
                AdvancedPlayer advancedPlayer = new AdvancedPlayer(this.getMP3(text));
                advancedPlayer.play();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        });

        playbackThread.setDaemon(false);
        playbackThread.start();
    }

    /**
     *
     * @param text đoạn văn bản cần phát âm
     * @param languageSpeech ngôn ngữ cần phát âm
     */
    private static void enterTextToSpeech(String text, String languageSpeech) {
        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.setLanguageSpeech(languageSpeech);

        textToSpeech.speakText(text);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String languageSpeech = "en_US";

        System.out.println("Nhập đoạn văn bản cần phát âm: ");
        String text = scanner.nextLine();

        enterTextToSpeech(text, languageSpeech);


    }
}

