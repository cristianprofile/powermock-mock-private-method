package es.cromero;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClassA.class)
public class ClassATest {


    //create a mock and use 1 real METHOD: publicMethod

    @Test
    public void testRealMethodMockingPrivate() throws Exception {

        ClassA classA = mock(ClassA.class);
        when(classA, "methodPrivate").thenReturn(83);
        when(classA.publicMethod()).thenCallRealMethod();
        int result = classA.publicMethod();
        assertEquals(result, 83);

    }


    //create a mock and use 2 real METHODS: publicMethod and methodPrivate

    @Test
    public void testRealMethods() throws Exception {

        ClassA classA = mock(ClassA.class);
        when(classA, "methodPrivate").thenCallRealMethod();
        when(classA.publicMethod()).thenCallRealMethod();
        int result = classA.publicMethod();
        assertEquals(result, 99);
        PowerMockito.verifyPrivate(classA, Mockito.times(1)).invoke("methodPrivate");

    }


    //create real object and use 1 real METHOD: publicMethod

    @Test
    public void testRealMethodsWithSpy() throws Exception {


        ClassB classB = mock(ClassB.class);
        ClassA classA = PowerMockito.spy(new ClassA(classB) );
        doReturn(83).when(classA,"methodPrivate");
        int result = classA.publicMethod();
        assertEquals(result, 83);
        PowerMockito.verifyPrivate(classA, Mockito.times(1)).invoke("methodPrivate");

    }


}
