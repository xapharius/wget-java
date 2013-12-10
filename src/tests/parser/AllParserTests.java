package tests.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestArgParser_CheckMethods.class,
		TestOptionFinder.class,
		TestArgParser.class,
		//trival cases has to be run last because of redirecting syso stream
		TestArgParser_TrivialCases.class})
public class AllParserTests {

}
