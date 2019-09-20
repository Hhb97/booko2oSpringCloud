package com.book.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class IDUUID {
    public static String getImageName() {
        return UUID.randomUUID().toString();
    }
    public  static Long getOrderNo(){//根据时间和随机数
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());

        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
       String orderNo = result+newDate;
        return Long.parseLong(orderNo);

    }


}