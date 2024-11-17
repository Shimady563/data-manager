package com.shimady.manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "major")
public class Major {
    @Id
    @SequenceGenerator(
            name = "major_id_seq",
            sequenceName = "major_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(generator = "major_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
}
