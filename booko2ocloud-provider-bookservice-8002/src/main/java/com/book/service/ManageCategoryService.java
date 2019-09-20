package com.book.service;

import com.book.common.ServerResponse;

import java.util.HashMap;
import java.util.List;

public interface ManageCategoryService {
    public ServerResponse getCategory(Integer CategoryId);
    public ServerResponse addCategory(Integer parentId,String categoryName);
    public ServerResponse deepCategory(Integer categoryId);
    public ServerResponse<String > updateCategoryName(Integer categoryId,String newName);
    public List<HashMap<Object,Object>> getAllCategory();
    public ServerResponse<String> batchDeleteCategory(String ids);
    public ServerResponse<String> updateCategoryByid(Integer categoryId,String categoryName);

}
