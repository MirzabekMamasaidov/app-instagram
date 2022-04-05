package uz.pdp.appinstagram.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appinstagram.entity.Attachment;
import uz.pdp.appinstagram.entity.Comment;
import uz.pdp.appinstagram.entity.Like;
import uz.pdp.appinstagram.entity.User;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private String description;

    private boolean isSaved;

    private boolean isTagged;

    private List<Integer> attachmentIds;

    private Integer userId;

    private List<UUID> commentIds;

    private List<UUID> likeIds;

    private List<Integer> taggedUserIds;




}
