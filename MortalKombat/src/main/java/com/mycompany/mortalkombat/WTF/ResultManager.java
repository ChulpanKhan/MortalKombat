/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Управляет записью и чтением результатов из Excel-файла.
 */
public class ResultManager {
    public static final String SCORE_FILE_PATH = "WTF.xlsx";
    private static final List<Result> results = new ArrayList<>();

    static {
        readResultsFromExcel();
    }
    
    // Попытка чтения из файла при запуске программы (можно явно вызвать где надо)
    public static void readResultsFromExcel() {
        Path path = Paths.get(SCORE_FILE_PATH);
        if (!Files.exists(path)) {
            return; // Нет файла — ничего не делаем, как ты и хочешь!
        }
        try (FileInputStream fis = new FileInputStream(path.toFile());
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            results.clear();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) continue;
                String name = row.getCell(1).getStringCellValue();
                int score = (int) row.getCell(2).getNumericCellValue();
                results.add(new Result(name, score));
            }
        } catch (Exception e) {
            System.out.println("smth went wrong with readResultsFromExcel() in ResultManager");
        }
    }

    public static void writeResultsToExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Результаты ТОП 10");

            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("№");
            headerRow.createCell(1).setCellValue("Имя");
            headerRow.createCell(2).setCellValue("Очки");

            for (int i = 0; i < Math.min(results.size(), 10); i++) {
                Result result = results.get(i);
                XSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(result.getName());
                row.createCell(2).setCellValue(result.getPoints());
            }

            try (FileOutputStream out = new FileOutputStream(SCORE_FILE_PATH)) {
                workbook.write(out);
            }
        } catch (Exception e) {
            System.out.println("smth went wrong with writeResultsToExcel() in ResultManager");
        }
    }

    public static void addResult(Result result) {
        results.add(result);
        results.sort(Comparator.comparing(Result::getPoints).reversed());
    }

    public static List<Result> getResults() {
        return results;
    }
}

