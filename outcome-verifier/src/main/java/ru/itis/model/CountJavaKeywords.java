package ru.itis.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "count_java_keywords")
public class CountJavaKeywords extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "java_keywords_id", referencedColumnName = "id")
    private JavaKeywords javaKeywords_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "repository_id", referencedColumnName = "id")
    private Repository repository_id;

    @Column(name = "count")
    private Long count;
}
