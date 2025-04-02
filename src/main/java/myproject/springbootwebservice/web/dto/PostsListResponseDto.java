package myproject.springbootwebservice.web.dto;

import lombok.Getter;
import myproject.springbootwebservice.domain.posts.Posts;

import java.time.format.DateTimeFormatter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
