package myproject.springbootwebservice.domain.posts;

import jakarta.persistence.*;
import lombok.*;
import myproject.springbootwebservice.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String author, String content, String title) {
        this.author = author;
        this.content = content;
        this.title = title;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


