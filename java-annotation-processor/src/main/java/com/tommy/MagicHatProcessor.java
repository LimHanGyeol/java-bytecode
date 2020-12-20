package com.tommy;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AutoService(Processor.class)
public class MagicHatProcessor extends AbstractProcessor {

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
                        .printMessage(Diagnostic.Kind.ERROR, "Magic annotation can not be used on " + elementName);
                return false;
            }
            processingEnv.getMessager()
                    .printMessage(Diagnostic.Kind.NOTE, "Processing" + elementName);
        }
        return true;
    }

}
