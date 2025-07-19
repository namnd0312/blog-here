package com.namnd.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Auditable implements Serializable {

    private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @Comment("Nội dung bài đăng")
    private String content;

    @Column(name = "title", length = 200, nullable = false)
    @Comment("Tiêu đề bài đăng")
    private String title;

    @Column(name = "author_id", nullable = false)
    @Comment("Mã tác giả, ánh xạ bảng users")
    private Long authorId;

}
