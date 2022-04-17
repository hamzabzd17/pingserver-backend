package io.Hamza.server.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.Hamza.server.enumeration.Status;
import io.Hamza.server.exceptions.EntityNotFoundException;
import io.Hamza.server.model.Server;
import io.Hamza.server.repo.ServerRepo;
import io.Hamza.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server :{}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server with ip address :{}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address;
        address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;

    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");

        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(long id) {
        log.info("Fetching server with id :{}", id);
        return serverRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("server not found"));
    }

    @Override
    public Server update(Server server) {
        log.info("Updating new server :{}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting  server :{}", id);
        serverRepo.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        String[] imageNames = { "server1.png", "server2.png", "server3.png", "server4.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }

}
