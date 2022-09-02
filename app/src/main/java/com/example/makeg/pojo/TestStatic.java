package com.example.makeg.pojo;

public class TestStatic {


    public TestStatic(){

    }
    private static TestStatic newInstance(String first){
        return newInstance(first, null);
    }
    private static TestStatic newInstance(String first, String last){
          return newInstance(first, last, null);
    }

    public static  TestStatic newInstance(String first, String last, String more){
        TestStatic te = new TestStatic();
        return te;
    }

}
