package task10;

public class ThreadForParsing implements Runnable {
    private String filename;

    @Override
    public void run() {
        ParseCSV parser = new ParseCSV();
        parser.parseFile(filename);
    }

    public ThreadForParsing(String filename) {
        this.filename = filename;
    }

}
