package convert;

import java.io.*;

/**
 * CSV转换为SQL
 *
 * @auther: MoreRoom
 * @date: 2018/11/22 11:04
 */
public class CsvConvertSQL {

    private static final String SOURCE_FILE = "D:\\apps\\SCSJ262423795_20181121.csv";

    private static final String TARGET_FILE = "D:\\apps\\import.sql";

    private static final String SQL_TEMPLATE = "INSERT INTO T_BANK_CARD_TEST (BANK_NAME, BANK_CODE, PROVINCE_NAME, PROVINCE_CODE, CITY_NAME, CITY_CODE, BRANCH_NAME, BRANCH_CODE) VALUES ('{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}');";

    private static void convertFile(File sourceFile, File targetFile) {
        try (FileReader fileReader = new FileReader(sourceFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(targetFile);
        ) {
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                String sql = convert(readLine);
                if (null != sql) {
                    sql += "\r\n";
                    fileWriter.write(sql);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convert(String source) {
        String sql = null;
        String[] columns = source.split(",");
        if (columns.length == 8) { // 与目标列数一致
            String sqlTemplate = SQL_TEMPLATE;
            for (String column : columns) {
                sqlTemplate = sqlTemplate.replaceFirst("\\{\\}", column);
            }
            sql = sqlTemplate;
        } else {
            System.out.println("error line -- " + source);
        }
        return sql;
    }

    public static void main(String[] args) throws IOException {
        File sourceFile = new File(SOURCE_FILE);
        if (!sourceFile.exists()) {
            throw new RuntimeException("文件不存在");
        }
        File targetFile = new File(TARGET_FILE);
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        convertFile(sourceFile, targetFile);
        System.out.println("done.");
    }

}