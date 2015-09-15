import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class WordGeneratorTest {

	@Test
	public void getWordCountTest() throws FileNotFoundException {
		
		WordGenerator story = new WordGenerator ("/Users/Bryson/207/csc207-speed-reader/SpeedReader/src/story.txt");
		String firstWord = story.next();
		String secondWord = story.next();
		assertEquals ("story.numWords = 2", 2, story.getWordCount());
	}
	
	@Test
	public void getSentenceCountTest() throws FileNotFoundException, IOException {
		
		WordGenerator story = new WordGenerator ("/Users/Bryson/207/csc207-speed-reader/SpeedReader/src/story.txt");
		while (story.hasNext1()) {
			String word = story.next();
		}
		assertEquals("story.numSentences = 7", 7, story.getSentenceCount());
	}

}
