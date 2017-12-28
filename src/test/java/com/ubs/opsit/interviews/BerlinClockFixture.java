package com.ubs.opsit.interviews;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.odf.LoadOdtFromURL;
import org.junit.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.format.DateTimeParseException;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  You should not need to
 * edit this class to complete the exercise, this is your definition of done.
 */
public class BerlinClockFixture {

    private TimeConverter berlinClock = new BerlinClock();
    private String theTime;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner(new LoadFromURL())
                .usingStepsFrom(this)                
                .withStory("berlin-clock.story")
                .run();
    }
    
    @Test
    public void berlinClockAcceptanceTestsInODT() throws Exception {
        aBehaviouralTestRunner(new LoadOdtFromURL())
                .usingStepsFrom(this)
                .withStory("berlin-clock-odt.odt")
                .run();
    }

    @When("the time is $time")
    public void whenTheTimeIs(@Named("time") String time) {
        theTime = time;
    }
    

    @Then("the clock should look like $result")
    public void thenTheClockShouldLookLike(@Named("result") String result) {
    	if (result.contains(";")){
    		String expected = String.join("\n", result.split(";"));
    		assertThat(berlinClock.convertTime(theTime)).isEqualTo(expected);
    	}else{
    		assertThat(berlinClock.convertTime(theTime)).isEqualTo(result);
    	}
    }
    
	@Then("time format exception like $")
	public void thenFormatException(String theExpectedBerlinClockOutput) {
		Throwable throwable = null;
		try {
			berlinClock.convertTime(theTime);
		} catch (DateTimeParseException e) {
			throwable = e;
		}
		assertNotNull(throwable);
		assertTrue(throwable.getMessage().toString().contains(theExpectedBerlinClockOutput));
	}
}
