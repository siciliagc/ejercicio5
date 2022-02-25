package com.example.ejercicio5;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/items")
public class ItemRestController {

    private Map<Long, Item> itemMap= new ConcurrentHashMap<>();
    private AtomicLong lastId= new AtomicLong();

    @GetMapping("/")
    public Collection <Item> getItems(){
        return itemMap.values();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable long id){
        Item item= itemMap.get(id);
        if (item!=null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)

    public Item createItem(@RequestBody Item item){
        long id = lastId.incrementAndGet();
        item.setId(id);
        itemMap.put(id, item);
        return item;
    }

    @PutMapping("/{id}")

    public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item nuevoItem){
        Item item = itemMap.get(id);
        if (item!=null){
            nuevoItem.setId(id);
            this.itemMap.put(id,nuevoItem);
            return new ResponseEntity<>(nuevoItem, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable long id){
        Item item = itemMap.remove(id);
        if (item!=null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
