package parser.test;

import com.intellij.testFramework.ParsingTestCase;
import com.intellij.vcsUtil.VcsFileUtil;
import org.jetbrains.agda.parser.AgdaParserDefinition;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;



public class AgdaParsingTest extends ParsingTestCase {
    static {
        System.setProperty("idea.platform.prefix", "Idea");
    }


    public AgdaParsingTest() {
        super("parserTests", "agda", new AgdaParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return ".";
    }

    public void testNat() throws Exception { doTest(true); }
    public void testLevel() throws Exception { doTest(true); }

}
