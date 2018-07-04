package es.cromero;

public class ClassA {

    private ClassB classB;

    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public int publicMethod()
    {
        int i = methodPrivate();
        return i;
    }


    public int publicMethodCallingClassB()
    {
        int i = classB.publicMethod();
        return i;
    }

    private int methodPrivate()
    {
        return 99;
    }

}
