package com.tommy;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AutoService(Processor.class)
public class MagicHatProcessor extends AbstractProcessor {

    private static final String PROCESSING = "Processing";
    private static final String PRINT_MESSAGE_CAN_NOT_USED_ANNOTATION = "Magic annotation can not be used on ";
    private static final String METHOD_PULL_OUT = "pullOut";
    private static final String CLASS_NAME = "MagicHat";
    private static final String PRINT_MESSAGE_FATAL_ERROR = "FATAL ERROR : ";

    // 이 프로세서가 어떤 어노테이션을 처리할 것 인가
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 프로세서가 처리할 어노테이션의 엘리먼트
        return new HashSet<>(Arrays.asList(Magic.class.getName()));
    }

    // 지원할 소스코드 버전
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // Element 소스코드의 구성요소를 말한다 (package, class, method)
    // 각각의 Element 들이 process 할 때 참조할 수 있다.
    // annotation processor 는 round 개념으로 동작한다.
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
        // elements 를 interface 에만 한정한다.
        for (Element element : elements) {
            Name elementName = element.getSimpleName();
            if (element.getKind() != ElementKind.INTERFACE) {
                processingEnv.getMessager()
                        .printMessage(Diagnostic.Kind.ERROR, PRINT_MESSAGE_CAN_NOT_USED_ANNOTATION + elementName);
                return false;
            }
            processingEnv.getMessager()
                    .printMessage(Diagnostic.Kind.NOTE, PROCESSING + elementName);

            TypeElement typeElement = (TypeElement) element;

            ClassName className = getClassName(typeElement);
            // Method 생성
            MethodSpec pullOut = getMethodSpec();

            // Class 생성
            TypeSpec magicHat = getTypeSpec(className, pullOut);

            // Source 생성
            Filer filer = processingEnv.getFiler();
            createSource(className, magicHat, filer);
        }
        return true;
    }

    private ClassName getClassName(TypeElement element) {
        return ClassName.get(element);
    }

    private MethodSpec getMethodSpec() {
        return MethodSpec.methodBuilder(METHOD_PULL_OUT)
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addStatement("return $S", "Rabbit!")
                .build();
    }

    private TypeSpec getTypeSpec(ClassName className, MethodSpec pullOut) {
        return TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(className)
                .addMethod(pullOut)
                .build();
    }

    private void createSource(ClassName className, TypeSpec magicHat, Filer filer) {
        try {
            JavaFile.builder(className.packageName(), magicHat)
                    .build()
                    .writeTo(filer);
        } catch (IOException e) {
            processingEnv.getMessager()
                    .printMessage(Diagnostic.Kind.ERROR, PRINT_MESSAGE_FATAL_ERROR + e);
        }
    }

}
