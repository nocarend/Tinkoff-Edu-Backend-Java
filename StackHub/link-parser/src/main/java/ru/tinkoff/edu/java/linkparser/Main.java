package ru.tinkoff.edu.java.linkparser;

public class Main {
    public static void main(String[] args) {
        String url1 = "https://github.com/sanyarnd/tinkoff-java-course-2022/";
        String url2 = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
        String url3 = "https://stackoverflow.com/search?q=unsupported%20link";
        System.out.println(new ExternalParser(url1).parse());
        System.out.println(new ExternalParser(url2).parse());
        System.out.println(new ExternalParser(url3).parse());
    }
}
