package ru.fizteh.fivt.students.kochetovnicolai.fileMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.fizteh.fivt.students.kochetovnicolai.shell.FileManager;

import java.io.File;
import java.io.IOException;

public class TestDistributedTableFactory extends FileManager {

    protected DistributedTableProviderFactory factory;
    protected File workingDirectory = new File("./TestDistributedTableFactory");

    @Before
    public void createWorkingDirectoryAndFactory() {
        if (workingDirectory.exists()) {
            recursiveRemove(workingDirectory, "TestDistributedTableFactory");
        }
        Assert.assertTrue(workingDirectory.mkdir());
        factory = new DistributedTableProviderFactory();
    }

    @After
    public void removeWorkingDirectoryAndFactory() {
        if (workingDirectory.exists()) {
            recursiveRemove(workingDirectory, "TestDistributedTableFactory");
        }
        factory = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void createProviderEmptyShouldFail() {
        factory.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createProviderOnFileShouldFail() throws IOException {
        String name = "file";
        File file = new File(workingDirectory.getPath() + File.separator + name);
        Assert.assertTrue(file.createNewFile());
        factory.create(name);
    }

    @Test
    public void createProvider() {
        Assert.assertTrue("failed create provider", factory.create("abcd") != null);
    }
}
