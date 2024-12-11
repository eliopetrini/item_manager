package com.itemmanager.itemmanager;

import com.itemmanager.qrcode.Label;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") //(origins = {"http://localhost:4200", "http://192.168.1.103:4200", "http://eliopetrini.synology.me"})
public class StoredItemsController {

    private final StoredItemsRepository storedItemsRepository;
    private final ChangeLogRepository changeLogRepository;
    private final LocationRepository locationRepository;

    public StoredItemsController(StoredItemsRepository storedItemsRepository, ChangeLogRepository changeLogRepository, LocationRepository locationRepository) {
        this.storedItemsRepository = storedItemsRepository;
        this.changeLogRepository = changeLogRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/items")
    public List<StoredItems> getStoredItems() {
        return (List<StoredItems>) storedItemsRepository.findAll();
    }

    @PostMapping("/items")
    void addStoredItem(@RequestBody StoredItems storedItems) {
        storedItemsRepository.save(storedItems);
    }

    @GetMapping("/delete")
    void deleteStoredItem(@RequestParam long id) { storedItemsRepository.deleteById(id);}

    @GetMapping(
    value = "/get-label",
    produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody String getLabel(@RequestParam String id, @RequestParam String name, @RequestParam String description, @RequestParam String appURL) throws Exception {
        Label label = new Label(Integer.parseInt(id), name, description, "item", appURL);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(label.generateLabel(), "jpg", bos);
        byte[] imageBytes = bos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    @GetMapping("/items/{itemID}")
    public Optional<StoredItems> getItemDetails(@PathVariable Long itemID) {
        return storedItemsRepository.findById(itemID);
    }

    @GetMapping("items/groups/category")
    public List<String> getCategories() {
        List<StoredItems> records = getStoredItems();
        List<String> results = new ArrayList<>();
        return records.stream().map(StoredItems::getItemCategory).distinct().collect(Collectors.toList());
    }

    @GetMapping("items/groups/location")
    public List<Location> getLocations() {
            return (List<Location>) locationRepository.findAll();
    }

}
