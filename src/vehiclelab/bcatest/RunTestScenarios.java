package bcatest;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RunTestScenarios {
    public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		
        ArrayList<BCATestScenario> testList = new ArrayList<>();

        System.out.println("Enter the package of testers.");

		String packageName = in.nextLine();		
		String directory = packageName.replace(".", "/");

		// Creates a new File instance for the given directory.
		File f = new File(directory);

		// Populates the array with names of files and directories
		String[] filenames = f.list();

		// For each pathname in the pathnames array
		for (String filename : filenames) {
			if (filename.endsWith(".java")) {
				filename = filename.substring(0, filename.lastIndexOf("."));
				Class cls = Class.forName(packageName + "." + filename);

				testList.add((BCATestScenario)(cls.getDeclaredConstructor().newInstance()));
			}
		}

        for (BCATestScenario test : testList ) {
            try {
                System.out.println();
                int failCount = test.runTest();

                if (failCount == 0) {
                    System.out.println(test.getClass().getName() + " passed.");
                }
                else {
                    System.out.println(test.getClass().getName() + " failed " + failCount + " cases.");
                }
            }
            catch (Throwable t) {
                System.out.println(test.getClass().getSimpleName() + " failed with an unexpected exception.");
                t.printStackTrace(System.out);
            }

        }

        System.out.println("\nTests completed.");
    }
}
