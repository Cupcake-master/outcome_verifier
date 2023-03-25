package ru.itis.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "java_keywords")
public class JavaKeywords extends BaseEntity{

    @Column(name = "name")
    public String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private Grade grade_id;
}
