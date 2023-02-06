package org.up.loom;

import com.example.loom.domain.Avatar;
import com.example.loom.domain.Info;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.incubator.concurrent.StructuredTaskScope;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.utils.HttpClient.*;

@WebServlet("/parallelj")
public class RemoteParallelRaw extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var delay = Long.parseLong(request.getParameter("delay"));
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<Avatar> reply1 = scope.fork(() ->
                    getRestTemplate().getForEntity("/avatar?delay=" + delay, Avatar.class).getBody());
            Future<Avatar> reply2 = scope.fork(() ->
                    getRestTemplate().getForEntity("/avatar?delay=" + delay, Avatar.class).getBody());
            scope.join();          // Join both forks
            scope.throwIfFailed(); // ... and propagate errors
            // Here, both forks have succeeded, so compose their results
            getMapper().writeValue(response.getWriter(), new Info(delay, List.of(reply1.get(), reply2.get())));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }













    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        var delay = Long.parseLong(request.getParameter("delay"));
//        try (ExecutorService e = executorService) {
//            Future<Avatar> reply1 = e.submit(() ->
//                    getRestTemplate().getForEntity("/avatar?delay=" + delay, Avatar.class).getBody());
//            Future<Avatar> reply2 = e.submit(() ->
//                    getRestTemplate().getForEntity("/avatar?delay=" + delay, Avatar.class).getBody());
//            response.setContentType("application/json");
//            try {
//                getMapper().writeValue(response.getWriter(), new Info(delay, List.of(reply1.get(), reply2.get())));
//            } catch (InterruptedException | ExecutionException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//
//
//    }
}