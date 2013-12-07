package tests.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestArgParser_CheckSaveToFile.class,
		TestArgParser_CheckURL.class, TestArgParser_TrivialCases.class,
		TestOptionFinder.class })
public class AllParserTests {

}
