package com.namnd.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Auditable implements Serializable {

    private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    @org.hibernate.annotations.Comment("Mã bài đăng, ánh xạ bảng post")
    private Long postId;


    @Column(name = "user_id", nullable = false)
    @org.hibernate.annotations.Comment("Mã user, ánh xạ bảng users")
    private Long userId;


    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @org.hibernate.annotations.Comment("Nội dung comment")
    private String content;
}
