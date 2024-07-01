package com.example.reddit.service;


import com.example.reddit.exceptionhandling.exception.LoadingException;
import com.example.reddit.exceptionhandling.exception.StorageException;
import com.example.reddit.indexModel.CommunityIndex;
import com.example.reddit.indexModel.PostIndex;
import com.example.reddit.indexRepository.CommunityIndexRepository;
import com.example.reddit.model.Post;
import com.example.reddit.repository.PostRepository;
import com.example.reddit.service.interfaces.FileService;
import com.example.reddit.service.interfaces.IndexingService;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.reddit.service.interfaces.IndexingService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.Tika;
import org.apache.tika.language.detect.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IndexingServiceImpl implements IndexingService {

	@Autowired
    private PostRepository postRepository;
	@Autowired
    private CommunityIndexRepository communityIndexRepository;
	@Autowired
    private LanguageDetector languageDetector;
	@Autowired
	private FileService fileService;
	
	

    @Transactional
    public String indexDocument(MultipartFile documentFile) {
        var newPost = new Post();
        var newCommunityIndex = new CommunityIndex();

        var title = Objects.requireNonNull(documentFile.getOriginalFilename()).split("\\.")[0];
        newPost.setTitle(title);
        newCommunityIndex.setName(title);

        var documentContent = extractDocumentContent(documentFile);
        if (detectLanguage(documentContent).equals("SR")) {
            newCommunityIndex.setDescriptionSr(documentContent);
        } else {
            newCommunityIndex.setDescriptionEn(documentContent);
        }

        var serverFilename = fileService.store(documentFile, UUID.randomUUID().toString());
        //newPost.setImagePath(serverFilename);
        //newCommunityIndex.setServerFilename(serverFilename);

        //newPost.setUser(/* set user if applicable */); // Example: newPost.setUser(currentUser);
        var savedPost = postRepository.save(newPost);

        //newCommunityIndex.setUser(/* set user if applicable */); // Example: newCommunityIndex.setUser(currentUser);
        communityIndexRepository.save(newCommunityIndex);

        return serverFilename;
    }

    private String extractDocumentContent(MultipartFile multipartPdfFile) {
        String documentContent;
        try (var pdfFile = multipartPdfFile.getInputStream()) {
            var pdDocument = PDDocument.load(pdfFile);
            var textStripper = new PDFTextStripper();
            documentContent = textStripper.getText(pdDocument);
            pdDocument.close();
        } catch (IOException e) {
            throw new LoadingException("Error while trying to load PDF file content.");
        }

        return documentContent;
    }

    private String detectLanguage(String text) {
        // Implement language detection logic as per your requirements
        return "SR"; // Example return, adjust as necessary
    }

    private String detectMimeType(MultipartFile file) {
        var contentAnalyzer = new Tika();

        String trueMimeType;
        String specifiedMimeType;
        try {
            trueMimeType = contentAnalyzer.detect(file.getBytes());
            specifiedMimeType =
                    Files.probeContentType(Path.of(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (IOException e) {
            throw new StorageException("Failed to detect mime type for file.");
        }

        if (!trueMimeType.equals(specifiedMimeType) &&
                !(trueMimeType.contains("zip") && specifiedMimeType.contains("zip"))) {
            throw new StorageException("True mime type is different from specified one, aborting.");
        }

        return trueMimeType;
    }
}
