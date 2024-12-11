package com.itemmanager.itemmanager;

import com.itemmanager.qrcode.Label;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") //(origins = {"http://localhost:4200", "http://192.168.1.103:4200"})
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    @GetMapping(
            value = "/locations/labels",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody String getLabel(@RequestParam String id, @RequestParam String name, @RequestParam String description, @RequestParam String appURL) throws Exception {
        Label label = new Label(Integer.parseInt(id), name, description, "location", appURL);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(label.generateLabel(), "jpg", bos);
        byte[] imageBytes = bos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    @PostMapping("/locations/delete")
    void deleteStoredItem(@RequestBody Location itemLocation) { locationRepository.delete(itemLocation);}

    @PostMapping("/locations/update")
    void update(@RequestBody Location itemLocation) { locationRepository.save(itemLocation);}

}
