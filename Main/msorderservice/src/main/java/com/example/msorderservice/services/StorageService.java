package com.example.msorderservice.services;

import com.example.msorderservice.entity.FileDataE;
import com.example.msorderservice.repository.FileDataRepository;
import com.example.msorderservice.util.FileUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

@Service
public class StorageService {

    @Autowired
    private FileDataRepository repository;

    public String uploadpdf(MultipartFile file,String Tid) throws IOException {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        FileDataE check = repository.findByTid(Tid);
        // data already existing
        if(check != null){
            //save new pdf in existing row
            String FileName = generatedString + file.getOriginalFilename() ;

            byte[] bytes = file.getBytes();
            FileDataE pdf = new FileDataE();
            check.setName(FileName);
            check.setImageData(bytes);
            check.setType(file.getContentType());
            FileDataE imageData = repository.save(check);
            return "file uploaded successfully : " + file.getOriginalFilename();
        }else {

        System.out.printf("upload Storage service");
        String FileName = generatedString + file.getOriginalFilename() ;

        byte[] bytes = file.getBytes();
        FileDataE pdf = new FileDataE();
        pdf.setName(FileName);
        pdf.setImageData(bytes);
        pdf.setType(file.getContentType());
        pdf.setTid(Tid);

       /* FileDataE imageData = repository.save(FileDataE.builder()
                .name(FileName)
                .type(file.getContentType())
                .imageData(FileUtil.compressImage(file.getBytes()))
                .tid(Tid).build()); */
        FileDataE imageData = repository.save(pdf);
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        }
        return null;
    }

    public FileDataE downloadpdf(String fileName){
        FileDataE dbImageData = repository.findByName(fileName);
        /*FileDataE dbData = null ;
        dbData.setImageData(dbImageData.get().getImageData());
        dbData.setName("Result");*/
        //byte[] images=FileUtil.decompressImage(dbImageData.get().getImageData());
        //byte[] images= dbImageData.get().getImageData();
        /*try {
            FileUtil.byteArrayToFile(images);
            //images.Headers.ContentType = new MediaTypeHeaderValue("application/pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        //File compressedFile = FileUtil.writeBytesToFile(compressedPdf, "compressed.pdf");
        return dbImageData;
    }

    public String uploadImage(MultipartFile file,String Tid) throws IOException {

        FileDataE imageData = repository.save(FileDataE.builder()
                .name(file.getName())
                .type(file.getContentType())
                .imageData(FileUtil.compressImage(file.getBytes()))
                .tid(Tid).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        FileDataE dbImageData = repository.findByName(fileName);
        byte[] images=FileUtil.decompressImage(dbImageData.getImageData());
        return images;
    }


}
