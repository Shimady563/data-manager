package com.shimady.manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_id_seq",
            sequenceName = "student_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(generator = "student_id_seq")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "student_group")
    private String group;

    @Column(name = "course")
    private Integer course;

    @Column(name = "has_record_book")
    private Boolean hasRecordBook = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;
}
