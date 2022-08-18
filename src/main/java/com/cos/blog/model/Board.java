package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity //entity는 가독성을 위해 클래스 바로 위에 위치하게 냅두자.
//ORM으로 Database를 생성한다는 의미이기에
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob //대용량 데이터 저장시 씀
    private String content; //섬머노트 라이브러리 html태그가 섞여서 디자인됨. 내용이 엄청 길어질수 있음

    private int count; // 조회수

    @ManyToOne // board가 many, user는 one ->한 명의 유저는 여러 글을 쓸 수 있다.
    @JoinColumn(name="userId")
    private User user; //글을 적은 사람. DB는 오브젝트를 저장할수 없기때문에 foreign key를 사용하지만, java에서는 오브젝트를 저장할수 있음.
    // 그러나 이러면 db에서 충돌이 나지 않는가? -> JPA ORM을 사용하면 이를 자동으로 해결해줌. 자동으로 foreignkey로 인식

    @CreationTimestamp
    private Timestamp createDate;
    
    //연관관계의 주인 -> FK를 가진 오브젝트
    @OneToMany(mappedBy = "board",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) //reply table에 있는 board를 넣어줌.
    // mappedBy가 있으면 연관관계의 주인이 아님을 의미함(FK가 아니다), 즉 db에 column을 만들지 마라. Join을 통해 값을 얻기 위해 필요한 것

    //
    //@JoinColumn(name="replyId") -> 필요가 없음. forein key까지 가지게 되면 하나의 row에는 하나의 값만을 가지는 데이터베이스의 특성(원자성)상
    //board하나에 100개의 reply가 있다고 가정했을때 그 db의 크기는 매우 커지게 됨.
    @JsonIgnoreProperties({"board"})
    //무한 참조를 방지하기 위한 어노테이션임.
    //Board를 통해 참조하는 reply에서, board를 무시한다는 것.
    // 만일 여기서 reply를 직접 참조해 가져오는거면 board도 가져올수 있음.
    @OrderBy("id desc")
    private List<Reply> reply; //하나의 게시글은 여러개의 reply가 필요함 그래서 여러개를 담을 List를 가져옴


    //Reply는 mapped라고 되있으니 데이터베이스에 있는 값이 아니므로 save시 넣어줄 필요가 없음. 나중에 select하기 위해 있는것.

}
