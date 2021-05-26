package ru.credit.calculator.console.model;

import java.io.File;
import java.util.Objects;

public class FileList {
    private File file;
    private String name;
    private long time;

    public FileList(File file){
      this.file = file;
      this.name = file.getName();
      this.time = file.lastModified();
    }

  public File getFile() {
    return file;
  }

  public String getName() {
    return name;
  }

  public long getTime() {
    return time;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FileList fileList = (FileList) o;
    return time == fileList.time &&
        Objects.equals(name, fileList.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, time);
  }
}
