package io.Hamza.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.Hamza.server.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);

}
