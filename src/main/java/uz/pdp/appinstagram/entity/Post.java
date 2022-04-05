package uz.pdp.appinstagram.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appinstagram.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends AbsEntity {

    private String description;

    private boolean isSaved = false;

    private boolean isTagged = false;

    @OneToMany
    private List<Attachment> attachments;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post")
    private List<Like> likes;

    @OneToMany
    private List<User> taggedUsers;



}
