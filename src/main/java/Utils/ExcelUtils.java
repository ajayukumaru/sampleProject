package Utils;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ExcelUtils {

    Workbook workbook;
    Sheet sheet;
    File file;

    public ExcelUtils(String filepath){
        file = new File(filepath);
    }

    public void writeExcel(){
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("First");

            Map<String, Object[]> map = new HashMap<>();
            map.put("0", new Object[]{"FirstName","LastName", "Age" });
            map.put("1", new Object[]{"Ajay","Kumar", 24 });
            map.put("2", new Object[]{"Kishore","Kumar", 23 });
            map.put("3", new Object[]{"Shwetha","Ravi", 23 });

            for(Map.Entry<String,Object[]> entry: map.entrySet()){
                Row row = sheet.createRow(Integer.parseInt(entry.getKey()));

                int cellNum = 0;
                Object[] obj = entry.getValue();

                for(Object object: obj){
                    Cell cell = row.createCell(cellNum++);
                    if(object instanceof String){
                        cell.setCellValue((String) object);
                    }
                    if(object instanceof Integer){
                        cell.setCellValue((Integer) object);
                    }
                }

            }

            try(FileOutputStream fos = new FileOutputStream("sample.xlsx")){
                workbook.write(fos);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private int getRowNum(String dataName){
        sheet = workbook.getSheet("First");
        int rowNum = 0;
        for(Row row: sheet){
            if(row.getCell(0).getStringCellValue().equals(dataName)){
                return rowNum;
            }
            rowNum++;
        }
        return 0;
    }

    public Map<String,String> getExcelData(String dataName) {
        Map<String, String> map = new HashMap<>();
        try(FileInputStream inputStream = new FileInputStream(file)){
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheet("First");
            int rowNum = 0;
            for(Row row: sheet){
                if(row.getCell(0).getStringCellValue().equals(dataName)){
                    rowNum = row.getRowNum();
                    break;
                }
                rowNum++;
            }

            Row row = sheet.getRow(rowNum);
            int celNum = 0;

            for (Cell cell : row) {
                var variable = switch (cell.getCellType()){
                    case NUMERIC -> Double.toString(cell.getNumericCellValue());
                    case STRING -> cell.getStringCellValue();
                    case BOOLEAN -> Boolean.toString(cell.getBooleanCellValue());
                    default -> "null";
                };
                map.put(sheet.getRow(0).getCell(celNum++).getStringCellValue(),variable);
                System.out.print(variable + " ");
            }
            System.out.println();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return map;
    }

}
