package springkpsgroup02.kps.Service.ServiceImplement;

import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springkpsgroup02.kps.Exception.InvalidException;
import springkpsgroup02.kps.Exception.NotFoundException;
import springkpsgroup02.kps.Service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final String bucketName = "preview-file";
    private final MinioClient minioClient;

    @Override
    public String uploadFile(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        // validate file extension allow only ending with .png, .svg, .jpg, .jpeg, or .gif
        List<String> allowFileExtensions = List.of("image/png", "image/svg+xml", "image/jpg", "image/jpeg", "image/gif");

        if( !allowFileExtensions.contains(file.getContentType()) || file.getContentType() == null) {
            throw new InvalidException("Profile image must be a valid image URL ending with .png, .svg, .jpg, .jpeg, or .gif");
        }

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String newFileName = UUID.randomUUID() + extension;

        BucketExistsArgs bucket = BucketExistsArgs.builder()
                .bucket(bucketName)
                .build();

        boolean isBucketExists = minioClient.bucketExists(bucket);

        if(!isBucketExists) {
            MakeBucketArgs makeBucket = MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build();

            minioClient.makeBucket(makeBucket);
        }

        PutObjectArgs objectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(newFileName)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();

        ObjectWriteResponse response = minioClient.putObject(objectArgs);

        return response.object();
    }

    @Override
    public Resource viewFileByFileName(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        GetObjectArgs object = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();

        InputStream result = minioClient.getObject(object);
        Resource resource = new InputStreamResource(result);

        return resource;
    }

}
