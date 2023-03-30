import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        JasonParser parser = new JasonParser();
        List<Map<String, String>> listOfMovies = parser.parse(body);

        stickerFactory sticker = new stickerFactory();

        for (Map<String, String> movie : listOfMovies) {
            String urlImage = movie.get("image");
            String fileName = movie.get("title") + ".png";
            InputStream inputStream = new URL(urlImage).openStream();

            sticker.create(inputStream, fileName);

            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
        }
    }
}
