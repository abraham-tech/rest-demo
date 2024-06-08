package miu.edu.rest_demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true, nullable = false, updatable = true) // User name should not be updatable for a real life project
    private String username;
    @Size(min = 8, max = 20)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
