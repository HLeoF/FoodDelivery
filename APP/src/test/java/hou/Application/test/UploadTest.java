package hou.Application.test;

import org.junit.jupiter.api.Test;

public class UploadTest {
    @Test
    public void test1(){
        String fileName = "123.png";
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(prefix);
    }
}
