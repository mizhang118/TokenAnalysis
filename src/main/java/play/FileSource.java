package play;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mz on 4/23/15.
 */
public class FileSource implements Source {
    private String sourceDir = null;
    private List fileList = null;

    public FileSource(String dir) {
        this.sourceDir = dir;
    }
    @Override
    public List getData() {
        if ( fileList != null ) {
            return fileList;
        }

        processData();
        return fileList;
    }

    @Override
    public void processData() {
        System.out.println("Start FileSource.processData ...");

        //todo: recursively collects all files under the input directory

        File dir = new File(sourceDir);
        if ( !dir.isDirectory() ) {
            System.err.print("The input directory does not exist or it is not a directory.");
            return;
        }

        fileList = new ArrayList();
        findFiles(dir);

        System.out.println("Collect " + fileList.size() + " files for next processing." );
        System.out.println("Finish FileSource.processData!");
    }

    private void findFiles(File dir) {
        if ( dir == null || !dir.exists() ) {
            return;
        }

        if ( dir.isDirectory() ) {
            File[] files = dir.listFiles();
            for ( int i = 0; i < files.length; i++ ) {
                File file = files[i];
                if ( file.isDirectory() ) {
                    findFiles(file);
                }
                else if ( file.isFile() ) {
                    String fileName = file.getName();
                    if ( fileName.indexOf(".java") > 0 || fileName.indexOf(".txt") > 0 || fileName.indexOf(".xml") > 0) {
                        fileList.add(file);
                    }
                }
            }
        }

        return;
    }
}
