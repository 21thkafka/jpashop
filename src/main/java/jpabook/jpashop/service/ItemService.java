package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long itemId, String name, int price, int stockQuentity){
        Item findItem = itemRepository.findOne(itemId); //영속성, 변경감지 == merge와 같은 기경 / 가급적 뱐걍감지 사용 권장
        findItem.setPrice(price);        //merge는 지정 안한 값을 null로 변경할 수 있음
        findItem.setName(name);
        findItem.setStockQuantity(stockQuentity);
        return findItem;

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
