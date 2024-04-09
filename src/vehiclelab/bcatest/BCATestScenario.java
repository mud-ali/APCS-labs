package bcatest;

public abstract class BCATestScenario {
    public static final double DELTA = .01;

    private int failed = 0;

    /** Runs the given test scenario and returns the number of failed tests. */
    public abstract int runTest();

    /** Returns the number of failed exceptions so far. */
    public int getFailedCount() {
        return failed;
    }

    /** Displays a formatted version of the failed message and increments the failed count. */
    private void assertionFailed(String failedMessage) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int i = 1;  // [0] is the Thread class
        while (ste[i].getClassName().contains("BCATestScenario")) {
            i++;
        }
        System.out.println(ste[i].getClassName() + "." + ste[i].getMethodName() + ":" + ste[i].getLineNumber() + " - Test Failed: " + failedMessage);
        failed++;
    }

    /** Verifies the executable throws exceptionType.  If the exception is not thrown,
     * the failedMessage is printed.
     */
    public void assertThrows(Class exceptionType, Executable executable, String failedMessage) {
        try {
            executable.execute();
            assertionFailed(failedMessage);
        }
        catch(Throwable t) {
            assertTrue(t.getClass() == exceptionType, "Unexpected exception type.");
        }
    }

    /** Verifies that 'expected' is close in value 'actual'.  Comparing floating point numbers
     * is subject to rounding errors.  In this function, the absolute distance between 'excepted'
     * and 'actual' must be less than 'delta' in order for the two numbers to be considered
     * equivalent. */
    public void assertEquals(double actual, double expected, double delta, String failedMessage) {
        assertTrue(Math.abs(expected-actual) <= delta, failedMessage + "; Expected: " + expected + "; Actual: " + actual);
    }

    /** Verifies the two string are equal.  If not, the failed message is printed. */
    public void assertEquals(String actual, String expected, String failedMessage) {
        assertTrue(expected.equals(actual), failedMessage + "; Expected: " + expected + "; Actual: " + actual);
    }

    /** Verifies the two ints are equal.  If not, the failed message is printed. */
    public void assertEquals(int actual, int expected, String failedMessage) {
        assertTrue(expected==actual, failedMessage + "; Expected: " + expected + "; Actual: " + actual);
    }
    
        /** Verifies that exp is true.  If not, the failed message is printed. 
     * Although you can call this method directly.  You are encoraged to use assertEquals 
     * whenever possible for more descriptive error messages.
    */
    public void assertTrue(boolean exp, String failedMessage) {
        if (!exp) {
            assertionFailed(failedMessage);
        }
    }

    /** Verifies that exp is false.  If not, the failed message is printed. */
    public void assertFalse(boolean exp, String failedMessage) {
        assertTrue(!exp, failedMessage);
    }

}