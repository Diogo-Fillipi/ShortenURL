package roadmapsh.project.shortenurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roadmapsh.project.shortenurl.DTO.HashCodeDTO;
import roadmapsh.project.shortenurl.DTO.URLDTO;
import roadmapsh.project.shortenurl.model.URLModel;
import roadmapsh.project.shortenurl.service.ShortenURLService;

import java.net.URI;

@RestController
public class ShortenURLController {

    @Autowired
    ShortenURLService shortenURLService;

    public ShortenURLController(ShortenURLService shortenURLService) {
       this.shortenURLService = shortenURLService;
    }

    @GetMapping("/{hashCode}")
    public ResponseEntity<Void> redirectToShortenURL(@PathVariable String hashCode) {

        String originalURL = shortenURLService.retrieveOriginalURL(hashCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalURL))
                .build();

    }

    @PostMapping("/shorten")
    public ResponseEntity<URLModel> originalURL(@RequestBody URLDTO urldto) {
        shortenURLService.createShortenURL(urldto.originalURL());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteShortenURL(@RequestBody HashCodeDTO hashCode) {
        shortenURLService.deleteShortenURL(hashCode.code());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
