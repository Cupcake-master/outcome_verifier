package ru.itis.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "repositories")
public class Repository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "git_path")
    private String git_path;

    @Column(name = "storage_path")
    private String storage_path;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "squad_id", referencedColumnName = "id")
    private Squad squad_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", comment='" + comment + '\'' +
                ", git_path='" + git_path + '\'' +
                ", storage_path='" + storage_path + '\'' +
                ", created=" + created +
                '}';
    }
}
