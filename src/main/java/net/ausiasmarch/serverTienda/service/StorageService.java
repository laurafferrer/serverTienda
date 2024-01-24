/* Code defines an interface StorageService that declares 
three methods to manage operations related to file storage */
package net.ausiasmarch.serverTienda.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {

    // Initialize the storage service
    void init();

    // Stores the provided file and return the stored file's name 
    String store(MultipartFile file);

    // Loads the resource (file) with the provided filename
    Resource loadAsResource(String filename);
    
}
