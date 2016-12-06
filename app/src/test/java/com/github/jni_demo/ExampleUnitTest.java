package com.github.jni_demo;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    static {        // 加载动态库
        System.loadLibrary("TestJNI");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 2);
        TestJNI testJNI = new TestJNI();
        System.out.println(testJNI.HelloWord("恭喜你,调用成功!"));

    }
}