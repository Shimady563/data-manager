package com.shimady.manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "discipline")
public class Discipline {
    @Id
    @SequenceGenerator(
            name = "discipline_id_seq",
            sequenceName = "discipline_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(generator = "discipline_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "terms")
    private List<Integer> terms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;
}
