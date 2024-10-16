package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.NewsRequest;
import com.toyshop.StoreToys_API.DTOs.respone.NewsRespone;
import com.toyshop.StoreToys_API.mapper.NewsMapper;
import com.toyshop.StoreToys_API.model.News;
import com.toyshop.StoreToys_API.repository.NewsRepository;
import com.toyshop.StoreToys_API.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final String folder = "uploads";

    @Override
    public List<NewsRespone> getAllNews() {
        return newsMapper.toNewsResponeList(newsRepository.findAll());
    }

    @Override
    public NewsRespone getNewsById(String id) {
        News news = this.findNewsById(id);
        return newsMapper.toNewsRespone(news);
    }

    @Override
    public NewsRespone createNews(NewsRequest newsRequest) {
        List<News.Content> list = new ArrayList<>();
        for (NewsRequest.ContentRequest content : newsRequest.getContent()) {
            try {
                News.Content newsContent = News.Content.builder()
                        .paragraph(content.getParagraph())
                        .image(this.store(content.getImage()))
                        .build();
                list.add(newsContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        News news = News.builder()
                .subject(newsRequest.getSubject())
                .title(newsRequest.getTitle())
                .content(list)
                .build();
        System.out.println(news);
        return newsMapper.toNewsRespone(newsRepository.save(news));
    }

    private News findNewsById(String id) {
        return newsRepository.findById(id).orElse(null); // bat exception
    }

    private Path getUploadDirLocation() throws IOException {
        Path folderPath = Paths.get(folder).toAbsolutePath().normalize();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        return folderPath;
    }

    private String store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadDir = getUploadDirLocation().resolve(fileName);
        Files.copy(file.getInputStream(), uploadDir, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    private void remove(String imgName) throws IOException {
        Path removeDir = this.getUploadDirLocation().resolve(imgName);
        if (Files.exists(removeDir)) {
            Files.delete(removeDir);
        }
    }
}
