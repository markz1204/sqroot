package calculation;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class SquareRootControllerTest extends AbstractTest{

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void sqroot() throws Exception {

        //Case 1: without input
        String uri = "/sqroot";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.equals("0.0"));

        //Case 2: with invalid input
        uri = "/sqroot?data=1,a,4,5,b";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.equals("0.0"));

        //Case 3: with valid input
        uri = "/sqroot?data=1,3,5,4,6,2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.equals("8.77"));
    }

}
