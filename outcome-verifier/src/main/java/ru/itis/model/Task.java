package ru.itis.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{

    @Column(name = "text")
    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    @OneToMany(mappedBy = "task_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Test> tests;

    @Override
    public String toString() {
        return "Task{" +
                "text='" + text + '\'' +
                ", state=" + state +
                '}';
    }
}
