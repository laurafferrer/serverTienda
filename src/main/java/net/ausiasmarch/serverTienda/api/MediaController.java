package net.ausiasmarch.serverTienda.api;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;
import java.nio.file.Files;

import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.service.StorageService;

@RestController
@RequestMapping("/media")
@AllArgsConstructor
public class MediaController {

    private final StorageService oStorageService;
    private final HttpServletRequest oRequest;

    @PostMapping("/")
    public Map<String, String> uploadFile (@RequestParam("file") MultipartFile oMultipartFile) {
        String path = oStorageService.store(oMultipartFile);
        String host = oRequest.getRequestURL().toString().replace(oRequest.getRequestURI(), "");
        String url = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(path).toUriString();

        return Map.of("url", url);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = oStorageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }
    
}
