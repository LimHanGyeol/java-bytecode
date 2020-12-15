package com.tommy.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    /**
     * 해당 구현한 내용의 프로젝트를
     * mvn install 로 로컬 m2 레퍼지토리에 배포할 수 있다.
     * 그리고 다른 프로젝트에서 아래의 내용으로 의존성을 다운 받은 후,
     * <groupId>com.tommy</groupId>
     * <artifactId>java-bytecode</artifactId>
     * <version>1.0.0-SNAPSHOT</version>
     *
     * @Inject 어노테이션으로 DI를 이용할 수 있다.
     */
    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        searchClassFields(classType, instance);
        return instance;
    }

    private static <T> void searchClassFields(Class<T> classType, T instance) {
        Arrays.stream(classType.getDeclaredFields())
                .forEach(field -> {
                    getAnnotation(instance, field);
                });
    }

    private static <T> void getAnnotation(T instance, java.lang.reflect.Field field) {
        if (field.getAnnotation(Inject.class) != null) {
            try {
                Object fieldInstance = createInstance(field.getType());
                field.setAccessible(true);
                field.set(instance, fieldInstance);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null)
                    .newInstance();
        } catch (InstantiationException |
                InvocationTargetException |
                IllegalAccessException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
