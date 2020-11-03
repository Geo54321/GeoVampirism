package me.Geo54321.GeoVampirism.Utility;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;

public class FileWorker {
    private JavaPlugin plugin;
    private String fileName;
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;
    private LinkedList<String> fileData;

    public FileWorker(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        fileData = new LinkedList<>();
        createFile();
    }

    public void createFile() {
        file = new File("plugins/GeoVampirism/" + fileName + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch(IOException e) {
                plugin.getLogger().log(Level.FINE,"Error Creating File.",e);
            }
        }
    }

    public void loadFile() {
        try {
            reader = new BufferedReader(new FileReader(file));
        }
        catch(FileNotFoundException e) {
            plugin.getLogger().log(Level.FINE,"Could Not Find File to Read.",e);
        }
        String line;
        try {
            while((line = reader.readLine()) != null)
                fileData.add(line);
            reader.close();
        }
        catch(IOException e) {
            plugin.getLogger().log(Level.FINE,"Error Reading from File",e);
        }
    }

    public void saveFile() {
        try {
            writer = new BufferedWriter(new FileWriter(file));
        }
        catch(IOException e) {
            plugin.getLogger().log(Level.FINE,"Could Not Find File to Write.",e);
        }
        try {
            for(String line : fileData) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e) {
            plugin.getLogger().log(Level.FINE,"Error Writing to File",e);
        }
    }

    public void createLine(String data) {
        fileData.add(data);
    }

    public void removeLine(String data) {
        fileData.remove(data);
    }

    public LinkedList<String> getData() {
        return fileData;
    }
}
