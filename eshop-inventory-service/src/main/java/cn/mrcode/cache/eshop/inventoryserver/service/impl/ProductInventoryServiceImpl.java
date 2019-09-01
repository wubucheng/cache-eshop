package cn.mrcode.cache.eshop.inventoryserver.service.impl;

import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mrcode.cache.eshop.inventoryserver.mapper.ProductInventoryMapper;
import cn.mrcode.cache.eshop.inventoryserver.model.ProductInventory;
import cn.mrcode.cache.eshop.inventoryserver.service.ProductInventoryService;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryMapper productInventoryMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void add(ProductInventory productInventory) {
        productInventoryMapper.add(productInventory);
        redisTemplate.opsForValue().set("product_inventory_" + productInventory.getId(), JSON.toJSONString(productInventory));
    }

    public void update(ProductInventory productInventory) {
        productInventoryMapper.update(productInventory);
        redisTemplate.opsForValue().set("product_inventory_" + productInventory.getId(), JSON.toJSONString(productInventory));
    }

    public void delete(Long id) {
        productInventoryMapper.delete(id);
        redisTemplate.delete("product_inventory_" + id);
    }

    public ProductInventory findById(Long id) {
        return productInventoryMapper.findById(id);
    }

}
