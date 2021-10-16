package net.vpein.timer.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metimer")
public class Metimer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "beschreibung")
    private String beschreibung;

    @Column(name = "sprecher")
    private String sprecher;

    @Column(name = "zeitpunkt")
    private Instant zeitpunkt;
}
