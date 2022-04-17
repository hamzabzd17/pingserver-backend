package io.Hamza.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import io.Hamza.server.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Server {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "ip address is required")
    private String ipAddress;
    private String name;
    private String memory;
    private String imageUrl;
    private String type;
    private Status status;

}
