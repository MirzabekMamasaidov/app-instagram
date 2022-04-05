package uz.pdp.appinstagram.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appinstagram.entity.Like;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CommentDto {

    private String text;
    private Integer userId;
    private UUID postId;
    private List<Like> likes;

}
