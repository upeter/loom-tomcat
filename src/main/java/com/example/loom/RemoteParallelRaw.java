package com.example.loom;

import com.example.loom.domain.Avatar;
import com.example.loom.domain.Info;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.utils.HttpClient.*;

@WebServlet("/parallelj")
public class RemoteParallelRaw extends HttpServlet {

    private final CloseableHttpClient httpClient = createClient();

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var delay = Long.parseLong(request.getParameter("delay"));
        try (ExecutorService e = executorService) {
            Future<Avatar> reply1 = e.submit(() ->
                    getMapper().readValue(httpClient.execute(HttpGetAvatar(delay)).getEntity().getContent().readAllBytes(), Avatar.class));
            Future<Avatar> reply2 = e.submit(() ->
                    getMapper().readValue(httpClient.execute(HttpGetAvatar(delay)).getEntity().getContent().readAllBytes(), Avatar.class));
            response.setContentType("application/json");
            try {
                getMapper().writeValue(response.getWriter(), new Info(delay, List.of(reply1.get(), reply2.get())));
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}