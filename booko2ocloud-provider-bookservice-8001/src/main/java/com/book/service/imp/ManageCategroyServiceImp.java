package com.book.service.imp;

import com.book.common.ResponseCode;
import com.book.common.ServerResponse;
import com.book.dao.CategoryMapper;
import com.book.pojo.Category;
import com.book.service.ManageCategoryService;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ManageCategroyServiceImp implements ManageCategoryService{
    @Autowired
    CategoryMapper categoryMapper;
    List<Integer> CategoryIdlist= new ArrayList<Integer>();
    public ServerResponse getCategory(Integer CategoryId){
        List<Category> list = categoryMapper.selectByparentId(CategoryId);
        if(list==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"该品类未找到");
        }
        return ServerResponse.createBySuccess(list);
     }
    public ServerResponse addCategory(Integer parentId,String categoryName){
        Map <String,String> result = new HashMap<>();
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        int ResultCont = categoryMapper.insert(category);
        if(ResultCont>0){
            System.out.println("id:"+category.getId());
            return ServerResponse.createBySuccess(category.getId().toString());
        }else return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"品类添加失败");
    }
    public ServerResponse deepCategory(Integer categoryId){
            List<Integer> data = new ArrayList<>();
            categoryDeep(categoryId,data);
        return ServerResponse.createBySuccess(data);
    }
    public void categoryDeep(int categoryId,List<Integer> data){
        data.add(categoryId);
        List<Category> list = categoryMapper.selectByparentId(categoryId);
        if(list!=null){
                for(Category category : list){
                    categoryDeep(category.getId(),data);
                }
        }
    }

    public ServerResponse<String > updateCategoryName(Integer categoryId,String newName){
        if(categoryId == null || StringUtils.isBlank(newName))
        {
            return  ServerResponse.createByErrorMessage("参数错误！");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(newName);
        category.setUpdateTime(new Date());
      int ResultCount=  categoryMapper.updateByPrimaryKeySelective(category);
      if(ResultCount>0){
          return  ServerResponse.createBySuccess("更新品类成功");
      }
        return ServerResponse.createBySuccessMessage("更新失败！");
    }
    public List<HashMap<Object,Object>> getAllCategory(){
        List<HashMap<Object,Object>> restult = new ArrayList<>();//返回结果
        Map<String,List<HashMap<String,String>>> temp = new HashMap<>();//
        List<HashMap<String, String> >listMap = new ArrayList<>();//pojo设置固定的json格式
        List<Category> list = categoryMapper.selectAllCategory();
        for(Category category : list){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("id",category.getId().toString());
            hashMap.put("parentid",category.getParentId().toString());
            hashMap.put("text",category.getName());

            listMap.add(hashMap);
        }
        for(HashMap hashMap : listMap){
            if(temp.containsKey(hashMap.get("parentid"))){
                temp.get(hashMap.get("parentid")).add(hashMap);
            }else {
                List<HashMap<String,String>> templist = new ArrayList<>();
               templist.add(hashMap);
               temp.put(hashMap.get("parentid").toString(),templist);

            }

        }
        for(HashMap hashMap :listMap){
            if(temp.containsKey(hashMap.get("id"))){
                hashMap.put("nodes",temp.get(hashMap.get("id")));
            }
            if(hashMap.get("parentid").toString().equals("0")){


                restult.add(hashMap);
            }

        }
        return restult;
    }
    public ServerResponse<String> batchDeleteCategory(String ids){

       String[] idarray= ids.split(",");



      int ResutlCount = categoryMapper.batchDeleteCategory(idarray);
       if(ResutlCount<1){
           return  ServerResponse.createByErrorMessage("删除分类失败");
       }
       return ServerResponse.createBySuccess("ok");
    }

    public ServerResponse<String> updateCategoryByid(Integer categoryId,String categoryName){
        if(categoryId==null||StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新参数错误");
        }
        Category category  = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        category.setUpdateTime(new Date());
        int ResultCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(ResultCount<1){
            return ServerResponse.createByErrorMessage("跟新失败");
        }
        return ServerResponse.createBySuccess("跟新成功！");

    }
}
