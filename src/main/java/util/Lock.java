package util;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// check if another process is running
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Lock {
    private static File f;
    private static FileChannel channel;
    private static FileLock lock;

    public Lock()
    {
        try
        {
            f = new File("process.lock");
            // Check if the lock exist
            System.out.println("exist = " + f.exists());
//            if (f.exists()) // if exist try to delete it
//                f.delete();
            // Try to get the lock
            channel = new RandomAccessFile(f, "rw").getChannel();
            lock = channel.tryLock();
            System.out.println("lock = " + lock);
            if(lock == null)
            {
                // File is lock by other application
                channel.close();
                throw new RuntimeException("Two instance cant run at a time.");
            }
            // Add shutdown hook to release lock when application shutdown
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);

        }
        catch(IOException e)
        {
            throw new RuntimeException("Could not start process.", e);
        }
    }

    public static void unlockFile() {
        // release and delete file lock
        try
        {
            if(lock != null)
            {
                lock.release();
                channel.close();
                f.delete();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    static class ShutdownHook extends Thread {
        public void run() {
            unlockFile();
        }
    }
}