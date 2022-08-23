package com.example.website2.item;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    //Just For Testing
//    @GetMapping("/item")
//    public Item hello(@RequestParam String name, Double price, Integer rating){
//        Item item = new Item(name, price, rating);
//        return item;
//    }
//
//    @PostMapping("/item")
//    public String saveItem(@RequestParam String name, Double price, Integer rating){
//        Item item = new Item(name, price, rating);
//        return ("item " + item.getName() + " was saved!");
//    }
//
//    @GetMapping("/items")
//    public List<Item> addList(){
//        List<Item> itemList = List.of(new Item("Book", 3.5, 4),
//                new Item("Game", 5.5, 5),
//                new Item("Shirt", 1.5, 3));
//        return itemList;
//    }

    @GetMapping("/item")
    public Item getItem(@RequestParam("id") Long id){
        return service.getItem(id);
    }

    @PostMapping("/item")
    public void addItem(@RequestBody Item item){
        service.addNewItem(item);
    }

    @DeleteMapping(path = "/item/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long id){
        service.deleteItem(id);
    }

    @Transactional
    @PutMapping(path = "/item/{itemId}")
    public void updateItem(
            @PathVariable("itemId") Long id,
            @RequestParam(required = false) String name){
        service.updateItemName(id, name);
    }

}
