package kr.kh.petvely.utils;

public class NoName {
    private static final NoName instance = new NoName();

    private NoName() {
    }
    
    public static NoName getInstance() {
        return instance;
    }

    public String getCurrentMethodName() {
    	return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public String getCurrentMethodName2() {
    	return Thread.currentThread().getStackTrace()[3].getMethodName();
    }
}
