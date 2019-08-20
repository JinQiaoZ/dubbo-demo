package com.mirana.provider;

public class ArrayTest01 {


    public  static int binarySearch(int[] arr,int key){
        int max,min,mid;
        min=0;
        max=arr.length-1;
        mid=(max+min)>>1;
        while (arr[mid]!=key) {
            if(key>arr[mid])
                min = mid + 1;
            else if(key<arr[mid])
                max = mid - 1;

            //判断元素是否存在。
            if(max<min)
                return -1;

            mid = (max+min)>>1;
        }
        return mid;
    }

}
