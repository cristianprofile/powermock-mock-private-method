package es.cromero;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClassA.class)
public class ClassAOtherTest {


    @Mock
    private ClassB classB ;

    private ClassA classA;

    //execute before every test
    @Before
    public void setUp() {
        //inject mock instances by constructor
        classA= PowerMockito.spy(new ClassA(classB) );
    }


    @Test
    public void testRealMethodMockingPrivate() throws Exception {

        doReturn(83).when(classA,"methodPrivate");
        int result = classA.publicMethod();
        assertEquals(result, 83);
        PowerMockito.verifyPrivate(classA, Mockito.times(1)).invoke("methodPrivate");

    }


    @Test
    public void testRealMethodMockingPrivate2() throws Exception {
        doReturn(87).when(classA,"methodPrivate");
        int result = classA.publicMethod();
        assertEquals(result, 87);
        PowerMockito.verifyPrivate(classA, Mockito.times(1)).invoke("methodPrivate");

        //mock b publicmethod
        when(classB.publicMethod()).thenReturn(1000);

        int resultB = classA.publicMethodCallingClassB();
        assertEquals(resultB, 1000);

    }

    }

