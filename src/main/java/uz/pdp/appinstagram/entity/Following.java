package uz.pdp.appinstagram.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appinstagram.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Following extends AbsEntity {

    @ManyToOne
    private User user;
}
