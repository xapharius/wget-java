package tests.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestArgParser_CheckMethods.class,
		TestArgParser_TrivialCases.class,
		TestOptionFinder.class })
public class AllParserTests {

}
