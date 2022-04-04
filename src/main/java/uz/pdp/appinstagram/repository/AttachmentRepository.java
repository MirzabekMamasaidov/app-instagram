package uz.pdp.appinstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appinstagram.entity.Attachment;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
