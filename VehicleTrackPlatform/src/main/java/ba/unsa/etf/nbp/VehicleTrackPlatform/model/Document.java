package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.Instant;

public class Document {
    private long id;
    private String fileName;
    private String fileType;
    private byte[] content;
    private Instant createdAt;

    public Document(long id, String fileName, String fileType, byte[] content, Instant createdAt) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.content = content;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
