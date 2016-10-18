# java_bytecode_playground

Digging deeper into the JVM.

Trying to get answers to the following questions:
* What is a java bytecode?
* What is basic class level bytecode?
* What is constant pool?
* How methods act and look like in java bytecode?
* How if clauses act and look like in java bytecode?
* ...

Idea is very simple. Create a simple class with a simple method. For example:
```java
package app.package.com;
public class Summator {
    public Summator() {
    }
    public int sum(int a, int b) {
        //return a + b;
        return 0;
    }
}
```
You see that sum(a, b) method is working incorectly. Let's write a simple test.

```java
package app.package.com;
import org.junit.Test;
import static org.junit.Assert.*;

public class SummatorTest {
    Summator summator = new Summator();
    @Test
    public void fourNotEqualsTwo() {
        assertNotEquals(summator.sum(2,2), 2);
    }
    @Test
    public void fourEqualsFour() {
        assertEquals(summator.sum(2,2), 4);
    }
}
```
You see that both tests will fail.

So, what is this challenge all about?
* 1) Compile SummatorTest class.
* 2) Compile Summator class.
* 3) Change Summator.class bytecode so that SummatorTest.class tests could pass.
* 4) Do not change SummatorTest.java

Interested? Check out the solution:
* [Summator Task Solution](https://github.com/ivarmah/java_bytecode_playground/blob/master/src/app/src/eu/ivarm/summator/ClassGen.java)
* [Minimator Task Solution](https://github.com/ivarmah/java_bytecode_playground/blob/master/src/app/src/eu/ivarm/minimator/ClassGen.java)
