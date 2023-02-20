package ru.itis.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tests")
public class Test extends BaseEntity{

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;
}
