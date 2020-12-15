package com.tommy.book;

@MyAnnotation // 기본값이 지정되어 있어 값을 안줘도 상관 없다.
public class Book {

    private static String B = "BOOK";

    @MyAnnotation(name = "gyeol", number = 1000)
    private static final String c = "BOOK";

    @MyAnnotation("hangyeol")
    private String a = "a";

    @MyAnnotation(value = "hangyeol", number = 500)
    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }

}
