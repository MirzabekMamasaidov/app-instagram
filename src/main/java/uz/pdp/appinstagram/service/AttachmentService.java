package uz.pdp.appinstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appinstagram.entity.Attachment;
import uz.pdp.appinstagram.entity.AttachmentContent;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.repository.AttachmentContentRepository;
import uz.pdp.appinstagram.repository.AttachmentRepository;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public ApiResponse upload(MultipartHttpServletRequest request) {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file!=null){
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);

            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            try {
                attachmentContent.setBytes(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            attachmentContent.setAttachment(savedAttachment);

            attachmentContentRepository.save(attachmentContent);
            return new ApiResponse("uploaded",true);
        }
        return new ApiResponse("Error",false);

    }
}
