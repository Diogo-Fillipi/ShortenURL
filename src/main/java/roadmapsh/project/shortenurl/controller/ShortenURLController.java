package roadmapsh.project.shortenurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import roadmapsh.project.shortenurl.DTOs.url.HashCodeDTO;
import roadmapsh.project.shortenurl.DTOs.url.NewURLDTO;
import roadmapsh.project.shortenurl.DTOs.url.URLDTO;
import roadmapsh.project.shortenurl.DTOs.url.URLResponseDTO;
import roadmapsh.project.shortenurl.services.url_services.ShortenURLService;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ShortenURLController {

    @Autowired
    ShortenURLService shortenURLService;

    public ShortenURLController(ShortenURLService shortenURLService) {
       this.shortenURLService = shortenURLService;
    }

    @GetMapping("/{hashCode}")
    public ResponseEntity<Void> redirectToShortenURL(@PathVariable String hashCode) {
        if(hashCode == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        String originalURL = shortenURLService.retrieveOriginalURL(hashCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalURL))
                .build();

    }

    @PostMapping("/shorten")
    public ResponseEntity<URLResponseDTO> toShortURL(@RequestBody @Validated URLDTO urldto) {
        if(shortenURLService.createShortenURL(urldto.originalURL()) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(shortenURLService.createShortenURL(urldto.originalURL()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteShortenURL(@RequestBody @Validated HashCodeDTO hashCode) {
        shortenURLService.deleteShortenURL(hashCode.code());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    public ResponseEntity<URLResponseDTO> updateURL(@RequestBody @Validated NewURLDTO urldto) {
        System.out.println(urldto.oldURLHashCode());
        System.out.println(urldto.newURL());
        System.out.println("testeeeee");
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(shortenURLService.updateShortenURL(urldto.oldURLHashCode(), urldto.newURL()));
    }

    @GetMapping("/stats/{hashCode}")
    public ResponseEntity<URLResponseDTO> shortenURLStats(@PathVariable String hashCode) {
        if(hashCode == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(shortenURLService.getStats(hashCode));
    }

}
