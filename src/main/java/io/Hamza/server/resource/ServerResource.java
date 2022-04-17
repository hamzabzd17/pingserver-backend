package io.Hamza.server.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.Hamza.server.enumeration.Status;
import io.Hamza.server.model.Response;
import io.Hamza.server.model.Server;
import io.Hamza.server.service.ServerService;

@RestController
@RequestMapping("/server")
public class ServerResource {
    private final ServerService serverService;

    @Autowired
    public ServerResource(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/list")
    ResponseEntity<Response> getServers() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity
                .ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("servers", serverService.list(30)))
                        .message("Servers retrieved").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

    }

    @GetMapping("/ping/{ipAddress}")
    ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity
                .ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", server))
                        .message(Objects.equals(server.getStatus(), Status.SERVER_UP) ? "Ping success" : "Ping failed")
                        .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

    }

    @PostMapping("/save")
    ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {

        return ResponseEntity
                .ok(Response.builder().timeStamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server saved")
                        .status(HttpStatus.CREATED).statusCode(HttpStatus.CREATED.value()).build());

    }

    @GetMapping("/get/{id}")
    ResponseEntity<Response> getServer(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity
                .ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", serverService.get(id)))
                        .message("server retrieved")
                        .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity
                .ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", serverService.delete(id)))
                        .message("server deleted")
                        .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

    }

    @GetMapping(path = "/image/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/" + filename));
    }

}
