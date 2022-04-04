package uz.pdp.appinstagram.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appinstagram.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends AbsEntity {

    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    @OneToMany(mappedBy = "comment")
    private List<Like> likes;

}
