package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;

import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        ArrayList<String> al = new ArrayList<String>(); 
        al.add("http://localhost:3000");
        al.add("http://localhost:3001");
        al.add("http://localhost:3002");
        ConsistentHash<String> ch=new ConsistentHash<String>(Hashing.md5(),3,al);
        for(int i=1,j=97;i<=10 && j<=106;i++,j++)
        {
        CacheServiceInterface cache = new DistributedCacheService(
                ch.get(i));
        cache.put(i,String.valueOf((char)j));
        System.out.println("put("+i+"=> "+ String.valueOf((char)j)+")");
        }
        System.out.println("Retrieving");
        for(int i=1;i<=10;i++)
        {
        	CacheServiceInterface cache = new DistributedCacheService(
                ch.get(i));
        	System.out.println("get("+i+")=> "+ cache.get(i));
        }
        System.out.println("Existing Cache Client...");
    }

}
