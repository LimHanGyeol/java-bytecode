package com.tommy.hat;

public class Magician {

    /**
     * VM Options
     * -javaagent:C:\Users\HANGYEOL\Documents\java-bytecode\java-magician-agent\target\java-magician-agent-1.0.0-SNAPSHOT.jar
     * 이 방식은 File System 에 있는 .class File 을 변경하는 것이 아니다.
     * javaagent 의 일은 classLoader 의 classLoading 할 때 적용이 된다.
     * 그래서 class 파일 자체가 바뀌진 않는다.
     * 로딩 할 때 javaagent(java-magician-agent) 를 거쳐서 변경된 바이트코드 자체를 읽어오기 때문에,
     * 변경된 바이트코드가 메모리에 들어가고 그대로 동작 하는 것이다.
     * 우리 시점에서 Hat 의 바이트코드는 바뀌지 않았지만 이미 메모리 내부에서 읽어올 때 바뀐 것이다.
     * 이러한 방식을 Transparent(비침투적인, 기존 코드를 전드리지 않는..) 하다고 한다.
     * @param args
     */
    public static void main(String[] args) {
        Moja moja = new MagicHat();
        System.out.println(moja.pullOut());
//        System.out.println(new Hat().pullOut());
    }

}
