import java.io.*;

/**
 * <p>A class to write comma separate value (CSV) files. Entries in
 * the CSV are written sequentially by row, and each row is written
 * sequentially by column. Entries can be strings or numerical values.</p>
 */

public class CSVWriter {
    /**
     * The path to the CSV file being written. 
     */
    private String path;

    /**
     * The current line/row of the file, which will be written on the
     * next call to <code>endLine</code>.
     */
    private StringBuilder curLine = new StringBuilder();

    /**
     * The <code>BufferedWriter</code> that will write to the file at
     * <code>path</code>.
     */
    private BufferedWriter out;

    /**
     * <p>Create a <code>CSVWriter</code> that will write to the file at
     * <code>path</code>. If a file with the specified path already
     * exists, it will be overwritten.</p>
     *
     * @param path the path (directory and filename) of the file to be
     * written
     */
    public CSVWriter(String path) {
	this.path = path;
	try {
	    out = new BufferedWriter(new FileWriter(path));
	} catch (IOException e) {
	    System.err.println("Could not create file: " + path);
	}
    }

    /**
     * <p>Append a <code>String</code> entry to the current row/line
     * of the CSV file.</p>
     *
     * @param str the entry to be appended to this row/line
     */
    public void addEntry(String str) {
	if (curLine.length() > 0) {
	    curLine.append(',');
	}
	curLine.append(str);
    }
    
    /**
     * <p>Append an <code>int</code> entry to the current row/line
     * of the CSV file.</p>
     *
     * @param value the entry to be appended to this row/line
     */
    public void addEntry(int value) {
	addEntry(Integer.toString(value));
    }

    /**
     * <p>Append a <code>long</code> entry to the current row/line
     * of the CSV file.</p>
     *
     * @param value the entry to be appended to this row/line
     */
    public void addEntry(long value) {
	addEntry(Long.toString(value));
    }

    /**
     * <p>Append a <code>float</code> entry to the current row/line
     * of the CSV file.</p>
     *
     * @param value the entry to be appended to this row/line
     */
    public void addEntry(float value) {
	addEntry(Float.toString(value));
    }

    /**
     * <p>Append a <code>double</code> entry to the current row/line
     * of the CSV file.</p>
     *
     * @param value the entry to be appended to this row/line
     */
    public void addEntry(double value) {
	addEntry(Double.toString(value));
    }
    
    /**
     * <p>End the current line (row) of the CSV file, and write its
     * contents to the file at path. Subsequent calls to
     * <code>addEntry</code> will be write to the following
     * row/line.</p>
     */
    public void endLine() {
	try {
	    out.write(curLine.toString() + "\n");
	} catch (IOException e) {
	    
	}
	
	curLine.setLength(0);
    }

    /**
     * <p>Close the file. <em>This must be done before the program
     * terminates, or else data loss may occur!</em></p>
     */
    public void close() {
	try {
	    out.close();
	    System.out.println("successfully wrote " + path);
	} catch (IOException e) {
	    System.err.println("could not close file " + path);
	}
	

    }
}
