package com.namnd.model;

import com.namnd.enums.ReactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "reaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reaction extends Auditable implements Serializable {

    private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    @Comment("Mã bài đăng, ánh xạ bảng post")
    private Long postId;

    @Column(name = "user_id", nullable = false)
    @Comment("Mã tác giả, ánh xạ bảng users")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Comment("action tương tác của bài đăng")
    private ReactionType type; // LIKE, DISLIKE

    @Column(name = "reacted_at", nullable = false)
    @Comment("Thời gian tương tác bài đăng")
    private LocalDateTime reactedAt;
}
