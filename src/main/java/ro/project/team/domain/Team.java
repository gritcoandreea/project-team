package ro.project.team.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "team")
    @JoinColumn(name = "captain_id", referencedColumnName = "id")
    private Captain captain;

    @OneToMany(mappedBy = "team")
    private Set<Robot> robots;
}
