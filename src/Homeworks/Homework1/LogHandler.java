package Homeworks.Homework1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LogHandler {
    private File logFile;

    LogHandler(String fileName) throws IOException {
        logFile = new File(fileName);
        if (!logFile.exists()) {
            this.logFile.createNewFile();
        }
    }

    public List<String> Read() throws IOException {
        List<String> lines = null;
        try (BufferedReader fileStream = new BufferedReader(new FileReader(this.logFile))) {
            lines = fileStream.lines().toList();
        } catch (Exception e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Ошибка: " + e);
            frame.setSize(300, 200);
            frame.setVisible(true);
        }
        return lines;
    }

    public void Write(String argLine) throws IOException {
        List<String> lines;
        try (BufferedReader tempReader = new BufferedReader(new FileReader(this.logFile))) {
            lines = tempReader.lines().toList();
        }
        try (BufferedWriter fileStream = new BufferedWriter(new FileWriter(this.logFile))) {
            for (String line : lines) {
                fileStream.append(line);
                fileStream.newLine();
            }

            fileStream.write(argLine);
            fileStream.newLine();
            fileStream.flush();
        }
    }
}
