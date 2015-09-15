import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;

 class WordGenerator {
	private int numSentences;
	private int numWords;
	private String fileName; //The path for the text file
	private Scanner text;
	
	/**
	 * The constructor for WordGenerator. filename is the path to the text file
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public WordGenerator (String filename) throws FileNotFoundException {
		this.fileName = filename;
		this.text = new Scanner (new File(this.fileName));
	}
	
	/**
	 * Decides whether or not the text file has more tokens to read.
	 * 
	 * @return true or false depending if there is text left to read
	 * @throws IOException
	 */
	public boolean hasNext1() throws IOException {
		return text.hasNext(); //Java already has a built in hasNext function.
	}
	
	/**
	 * Obtains the next token from the text file
	 * 
	 * @return the next word (token) in the text file
	 */
	public String next() {
		this.numWords++;
		String word = text.next();
		char last = word.charAt(word.length() - 1);
		if (last == '.' || last == '!' || last == '?') {
			this.numSentences++;
		}
		return word;
	}
	
	/**
	 * Obtains the number of words read in by the word generator.
	 * 
	 * @return the number of words
	 */
	public int getWordCount() {
		return this.numWords;
	}
	
	/**
	 * Obtains the number of sentences read in by the word generator.
	 * 
	 * @return the number of sentences
	 */
	public int getSentenceCount() {
		 return this.numSentences;
	}

	public static void main (String[] args) throws FileNotFoundException, IOException, InterruptedException {
		
		if (args.length != 5) {
			System.out.printf ("Error: Expected 5 arguments, given %d\n", args.length);
			System.out.println ("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
		}
		
		String filename = args[0];
		int width = Integer.parseInt (args[1]);
		int height = Integer.parseInt (args[2]);
		int fontSize = Integer.parseInt (args[3]);
		double wpm = (double) Integer.parseInt (args[4]);
		
		WordGenerator story = new WordGenerator (filename);
		DrawingPanel panel = new DrawingPanel (width, height);
		Graphics g = panel.getGraphics();
		Font f = new Font ("Courier", Font.BOLD, fontSize);
		g.setFont(f);
		
		
		/*
		 * Included 2 improvements to original program:
		 * Centered Text
		 * Program sleeps twice as long after a sentence.
		 */
		while (story.hasNext1()) {
			double factor = 1;
			int sentCount = story.getSentenceCount();
			String word = story.next();
			FontMetrics fm = g.getFontMetrics();
			int w = fm.stringWidth (word);
			int h = fm.getHeight();
			g.drawString (word, (int) ((width / 2.0) - (w / 2.0)), (int) ((height / 2.0) - (h / 2.0))); //display the word in the panel window
			if (sentCount != story.getSentenceCount()) { //sleep longer after end of a sentence
				factor = 2; //factor could be changed based on user's choice
			}
			Thread.sleep ((long) (factor * 60000.0 / wpm)); //sleep for a certain time based on wpm
			panel.clear();
		}
		
		System.out.printf ("Number of words printed: %d\n", story.getWordCount());
		System.out.printf ("Number of sentences printed: %d\n", story.getSentenceCount());
	}

}
