package com.example.website2.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getItems(){
        return repository.findAll();
    }

    public void addNewItem(Item item) {
        Optional<Item> itemOptional = repository
                .findItemsByName(item.getName());
        if (itemOptional.isPresent()){
            throw new IllegalStateException("Already saved");
        }
        else{
            repository.save(item);
        }
        System.out.println(item);
    }

    public void deleteItem(Long id) {
        boolean exists = repository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Item Does Not Exist");
        }
        repository.deleteById(id);

    }

    public void updateItemName(Long id, String name) {
        boolean exists = repository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Item Does Not Exist");
        }
        else{
            repository.findById(id).get().setName(name);
        }
    }
}
