package org.levelup.university.damain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "university")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class University {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long universityID;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "foundation_year")
    private Integer foundationYear;

    public University(String name, String shortName, Integer foundationYear) {
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
    }
}
