package examples.flex2.camera.snapshot.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.seasar.flex2.message.io.ByteArray;
import org.seasar.framework.util.FileOutputStreamUtil;

import examples.flex2.camera.snapshot.SnapshotServiceConfig;
import examples.flex2.camera.snapshot.dto.SnapshotDto;
import examples.flex2.camera.snapshot.service.SnapshotService;
import examples.flex2.camera.util.naming.FileNameResolver;

public class SnapshotServiceImpl implements SnapshotService {

    private FileNameResolver fileNameResolver;

    private SnapshotServiceConfig snapshotServiceConfig;

    public FileNameResolver getFileNameResolver() {
        return fileNameResolver;
    }

    public SnapshotServiceConfig getSnapshotServiceConfig() {
        return snapshotServiceConfig;
    }

    public String save(SnapshotDto snapshot) {
        ByteArray bytearray = snapshot.getSource();
        bytearray.uncompress();
        byte[] buffer = bytearray.getBufferBytes();
        File file = createSnapshotFile();
        FileOutputStream fileOutputSteam = FileOutputStreamUtil.create(file);
        try {
            fileOutputSteam.write(buffer);
            fileOutputSteam.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputSteam != null) {
                try {
                    fileOutputSteam.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return snapshotServiceConfig.getRootUri() + file.getName();
    }

    public void setFileNameResolver(FileNameResolver fileNameResolver) {
        this.fileNameResolver = fileNameResolver;
    }

    public void setSnapshotServiceConfig(SnapshotServiceConfig serviceConfig) {
        this.snapshotServiceConfig = serviceConfig;
    }

    private final String createFileName() {
        return snapshotServiceConfig.getPrefix() + fileNameResolver.getFileName(null)
                + snapshotServiceConfig.getSuffix();
    }

    private final File createSnapshotFile() {
        File saveDir = new File(snapshotServiceConfig.getRootPath());
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        return new File(snapshotServiceConfig.getRootPath() + createFileName());
    }
}
