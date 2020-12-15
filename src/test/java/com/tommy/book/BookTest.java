package com.tommy.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    private Constructor<?> getConstructor(Class<?>... parameterTypes) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> bookClass = Class.forName("com.tommy.book.Book");
        return bookClass.getConstructor(parameterTypes);
    }

    private Object newInstance(Constructor<?> constructor, String param) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        return constructor.newInstance(param);
    }

    @Test
    @DisplayName("리플렉션으로 Default 생성자 생성")
    void newInstanceIsDefault() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = getConstructor(null);
        Book book = (Book) constructor.newInstance();

        assertThat(book).isNotNull();
    }

    @Test
    @DisplayName("리플렉션으로 매개변수가 존재하는 생성자 생성")
    void newInstance() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = getConstructor(String.class);
        Book book = (Book) newInstance(constructor, "myBook");

        assertThat(book).isNotNull();
        assertThat(book.getA()).isEqualTo("myBook");
    }

    @Test
    @Order(1)
    @DisplayName("리플렉션으로 Field 가져오기")
    void getField() throws NoSuchFieldException, IllegalAccessException {
        Field bookField = Book.class.getDeclaredField("B");
        bookField.setAccessible(true);

        assertThat(bookField.get(null)).isNotNull();
    }

    @Test
    @DisplayName("리플렉션으로 private static field 값 수정하기")
    void updatePrivateField() throws NoSuchFieldException, IllegalAccessException {
        Field bookField = Book.class.getDeclaredField("B");
        bookField.setAccessible(true);

        assertThat(bookField.get(null)).isNotNull();
        assertThat(bookField.get(null)).isEqualTo("BOOK");

        bookField.set(null, "UPDATE_BOOK");
        assertThat(bookField.get(null)).isEqualTo("UPDATE_BOOK");
    }

    @Test
    @DisplayName("리플렉션으로 인스턴스에 해당하는 Field 가져오기")
    void getInstanceField() throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = getConstructor(String.class);

        Book book = (Book) newInstance(constructor, "HangyeolBook");

        Field bookField = Book.class.getDeclaredField("a");
        bookField.setAccessible(true);

        assertThat(bookField.get(book)).isEqualTo("HangyeolBook");
    }

    @Test
    @DisplayName("리플렉션으로 인스턴스에 해당하는 Field 수정하기")
    void getInstanceFieldUpdate() throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = getConstructor(String.class);

        Book book = (Book) newInstance(constructor, "HangyeolBook");

        Field bookField = Book.class.getDeclaredField("a");
        bookField.setAccessible(true);
        bookField.set(book, "HangyeolBookUpdate");

        assertThat(bookField.get(book)).isEqualTo("HangyeolBookUpdate");
    }

    @Test
    @DisplayName("리플렉션으로 인스턴스에 정의된 Method 가져오기")
    void getMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = getConstructor(String.class);

        Book book = (Book) newInstance(constructor, "myBook");

        Method method = Book.class.getDeclaredMethod("f");
        method.setAccessible(true);
        method.invoke(book);
    }

    @Test
    @DisplayName("리플렉션으로 public sum method 가져오기")
    void getSum() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = getConstructor(String.class);

        Book book = (Book) newInstance(constructor, "myBook");

        Method method = Book.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) method.invoke(book, 1, 10);
        assertThat(invoke).isEqualTo(11);
    }
}
