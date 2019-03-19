package com.tenpay.wxwork.salary.util.download;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.tenpay.fingate.util.StrUtil;
import com.tenpay.wxwork.salary.util.DateUtil;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


/**
 * POI 工具类<br/>
 * 导入、导入Excel<br/>
 *
 * @author yeshujun
 */
public class ExcelUtil {

    public final static String errorMsgFormat = "第{0}行，第{1}列";
    public final static String errorMsgFormatEn = "Column {1} of row {0}";

    /**
     *根据file path获取当前workbook
     * @param filePath
     * @return
     * @throws Exception
     */
    public static XSSFWorkbook getXSSFWorkbook(String filePath) throws Exception {
        return getXSSFWorkbook(new FileInputStream(filePath));
    }
    /**
     * 根据InputStream获取当前workbook
     * @param in
     * @return
     * @throws Exception
     */
    public static XSSFWorkbook getXSSFWorkbook(InputStream in) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(in);
        IOUtils.closeQuietly(in);
        return wb;
    }

    /**
     * 读取 excel到List
     * 列固定，循环行
     *
     * 从第一行开始解析表头
     *
     * @param <T>        excel的列对应的java类
     * @param file       传入的excel文件
     * @param sheetPage  sheet角标(excel的第几个sheet)
     * @param startRow   开始读取的行号
     * @param startCell  开始读取的列
     * @param clazz      excel每一行映射的java类(需要加注解判断模板)
     * @return
     * @throws Exception
     */
    public static <T> List<T> fromExcel(MultipartFile file,int sheetPage, int startRow, int startCell,
                                        Class<? extends T> clazz) throws Exception {

        XSSFWorkbook wb = getXSSFWorkbook(file.getInputStream());
        Sheet sheet = wb.getSheetAt(sheetPage);
        List<T> list = new ArrayList<T>();
        ArrayList<String> fieldNames = checkExcel(sheet,sheetPage,clazz);
        if(fieldNames==null){
            return null;
        }
        T obj = null;
        int totalRows = sheet.getLastRowNum();
        Row row = null;
        Cell cell = null;
        for (int i = startRow; i <= totalRows; i++) {//循环行
            obj = clazz.newInstance();
            row = sheet.getRow(i);
            if(row==null){
                return list;
            }
            int cellNum = 0;
            String errorMessage = "";
            for (int j = 0; j < fieldNames.size(); j++) {//循环列
                cellNum = j + startCell;
                //列值
                cell = row.getCell(cellNum);

                Object cellValue = getCellValue(cell);
                //
                setFieldValue(obj, fieldNames.get(cellNum), cellValue);
//                String flag = setFieldValue(obj, fieldNames.get(cellNum), cellValue);
//                if(flag!=""){
//                	Field field = getAccessibleField(obj, "errorMsg");
//                	errorMessage += "第"+(i+1)+"行，第"+(j+1)+"列，数据错误。";
//                	field.set(obj, errorMessage);
//                }

//                //数据校验
//                Field field = getAccessibleField(obj, fieldNames.get(cellNum));
//
//                //注解方式校验
//                Annotation[] annotations = field.getAnnotations();
//                if (annotations != null && annotations.length > 0) {
//                    for (Annotation annotation : annotations) {
//
//                        //其他校验
//                    }
//                }
            }
            list.add(obj);
        }
        return list;
    }


    /**
     * 读取 excel到List
     * 列固定，循环行
     *
     * 校验企业自定义发薪明细模板的表头
     *
     * @param file       传入的excel文件
     * @param sheetPage  sheet角标(excel的第几个sheet)
     * @param startRow   开始读取的行号
     * @param startCell  开始读取的列
     * @return
     * @throws Exception
     */
    public static ArrayList<LinkedHashMap<String,String>> fromDefinedExcel(MultipartFile file,int sheetPage, int startRow, int startCell,
                                                                           ArrayList<String> listField,int salarySize,int deductionSize) throws Exception {

        XSSFWorkbook wb = getXSSFWorkbook(file.getInputStream());
        Sheet sheet = wb.getSheetAt(sheetPage);
        ArrayList<LinkedHashMap<String,String>> list = new ArrayList<LinkedHashMap<String,String>>();
        ArrayList<String> fieldNames = checkDefinedExcel(sheet,sheetPage,listField,salarySize,deductionSize);
        if(fieldNames==null){
            return null;
        }
        LinkedHashMap<String,String> map;
        int totalRows = sheet.getLastRowNum();
        Row row = null;
        Cell cell = null;
        for (int i = startRow; i <= totalRows; i++) {//循环行
            map = new LinkedHashMap<String,String>();
            row = sheet.getRow(i);
            if(row==null){
                return list;
            }
            for (int j = 0; j < fieldNames.size(); j++) {//循环列
                //列值
                cell = row.getCell(j);

                Object cellValue = getCellValue(cell);
                if (cellValue == null) cellValue = "";
                map.put(listField.get(j),cellValue.toString());
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 检验excel模板
     *
     *  @param sheet      sheet
     */
    public static ArrayList<String> checkExcel(Sheet sheet,int sheetPage,Class<? extends Object> clazz){
        //boolean flag = true;
        ArrayList<String> strs = new ArrayList<String>();
        Map<Integer, String> map = new HashMap<Integer,String>();
        Field[] fields = clazz.getDeclaredFields();
        //获取类中注释部分中文名
        for(int i=0;i<fields.length;i++)
        {
            if(fields[i].getAnnotation(ExcelDownloadAnnotation.class)!=null)
            {
                int mark = fields[i].getAnnotation(ExcelDownloadAnnotation.class).mark();
                if((mark==-1)||(sheetPage==mark)){
                    strs.add(fields[i].getName());
                    String cnName = fields[i].getAnnotation(ExcelDownloadAnnotation.class).cnName();
                    int order = fields[i].getAnnotation(ExcelDownloadAnnotation.class).order();
                    map.put(order, cnName);
                }
            }
        }
        if(sheet!=null)
        {
            Row rootRow = sheet.getRow(0);
            if(rootRow!=null)
            {
                //判断上传excel分表头与类中的中文名对比，不一样直接返回
                for(int cellNum = 0;cellNum<map.size();cellNum++)
                {
                    String cellName = map.get(cellNum+1);
                    if(rootRow.getCell(cellNum)==null||!rootRow.getCell(cellNum).toString().equals(cellName))
                    {
                        return null;
                    }
                }
            }else{
                return null;
            }
        }else{
            return null;
        }
        return strs;
    }

    /**
     * 检验excel模板
     *
     *  @param sheet      sheet
     */
    public static ArrayList<String> checkDefinedExcel(Sheet sheet,int sheetPage,ArrayList<String> listField,int salarySize,int deductionSize){
        if(sheet!=null)
        {
            Row rootRow = sheet.getRow(0);//获取表头的字段
            Row rootRow_1 = sheet.getRow(1);//获取表头的字段
            if(rootRow!=null)
            {
                //判断上传excel分表头与字段表的中文名对比，不一样直接返回
                for(int cellNum = 0;cellNum < 3;cellNum++)
                {
                    String cellName = listField.get(cellNum).split("-")[1];
                    if(rootRow.getCell(cellNum)==null||!rootRow.getCell(cellNum).toString().equals(cellName))
                    {
                        return null;
                    }
                }
                for(int cellNum = 3 + salarySize;cellNum < 4 + salarySize;cellNum++)
                {
                    String cellName = listField.get(cellNum).split("-")[1];
                    if(rootRow.getCell(cellNum)==null||!rootRow.getCell(cellNum).toString().equals(cellName))
                    {
                        return null;
                    }
                }
                for(int cellNum = 4 + salarySize + deductionSize;cellNum < 6 + salarySize + deductionSize;cellNum++)
                {
                    String cellName = listField.get(cellNum).split("-")[1];
                    if(rootRow.getCell(cellNum)==null||!rootRow.getCell(cellNum).toString().equals(cellName))
                    {
                        return null;
                    }
                }
            }else{
                return null;
            }
            if(rootRow_1!=null)
            {
                //判断上传excel分表头与字段表的中文名对比，不一样直接返回
                for(int cellNum = 3;cellNum < 3 + salarySize;cellNum++)
                {
                    String cellName = listField.get(cellNum).split("-")[1];
                    if(rootRow_1.getCell(cellNum)==null||!rootRow_1.getCell(cellNum).toString().equals(cellName))
                    {
                        return null;
                    }
                }
                for(int cellNum = 4 + salarySize;cellNum < 4 + salarySize + deductionSize;cellNum++)
                {
                    String cellName = listField.get(cellNum).split("-")[1];
                    if(rootRow_1.getCell(cellNum)==null||!rootRow_1.getCell(cellNum).toString().equals(cellName))
                    {
                        return null;
                    }
                }
                for(int cellNum = 6 + salarySize + deductionSize;cellNum < 7 + salarySize + deductionSize;cellNum++)
                {
                    String cellName = listField.get(cellNum).split("-")[1];
                    if(rootRow_1.getCell(cellNum)==null||!rootRow_1.getCell(cellNum).toString().equals(cellName))
                    {
                        return null;
                    }
                }
            }else{
                return null;
            }
        }else{
            return null;
        }
        return listField;
    }


    /**
     * 获取List到excel
     * 列固定，循环行
     *
     * @param <T>
     * @param startRow   开始写入的行号
     * @param dataList      list数据
     * @param fieldNames T的属性名，与excel的列相同的顺序
     * @return
     * @throws Exception
     */
    public static <T> void setCellByList(Sheet sheet, int startRow, List<T> dataList, String... fieldNames) {
        //模板行
        Row rowTemplate = sheet.getRow(startRow);
        Object cellValue = null;
        if (dataList != null && dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                for (int j = 0; j < fieldNames.length; j++) {
                    cellValue = null;
                    if (StrUtil.isNotEmpty(fieldNames[j])) {
                        cellValue = getFieldValue(dataList.get(i), fieldNames[j]);
                    }

                    setCellByValue(sheet, startRow + i, j, null, cellValue, rowTemplate);
                }
            }
        }
    }


    /**
     * 将List数据导出excel的指定行
     * 行固定，循环列
     *
     * @param startCell 开始的列
     * @throws
     * @pparam sheetAt sheet号
     * @pparam rowNum 指定的行号
     * @pparam datas List数据
     * @pparam fieldNames T的属性名
     * @pparam
     */
    public static <T> void setCellInOneRowByList(Sheet sheet, int rowNum, int startCell,
                                                 List<T> datas, String... fieldNames) throws Exception {
        Object cellValue = null;
        //将第一个单元格的样式作为整行所有单元格的样式
        CellStyle cellStyle = sheet.getRow(rowNum).getCell(startCell).getCellStyle();
        int fieldLength = fieldNames.length;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {//10

                for (int j = 0; j < fieldNames.length; j++) {//2
                    cellValue = null;
                    if (StrUtil.isNotEmpty(fieldNames[j])) {
                        cellValue = getFieldValue(datas.get(i), fieldNames[j]);
                    }
                    setCellByValue(sheet, rowNum, j + startCell, cellStyle, cellValue);
                }
                startCell = (startCell + 1) + (fieldLength - 1);
            }
        }
    }


    /**
     * 将List数据，写入到excel
     * 适合以列表展现数据的情况
     * 列固定，循环行
     *
     * @param <T>
     * @param startRow   开始写入的行号
     * @param startCell  开始写入的列号
     * @param datas      list数据
     * @param fieldNames T的属性名，与excel的列相同的顺序
     * @return
     * @throws Exception
     */
    public static <T> void setCellByListList(Sheet sheet, int startRow, int startCell,
                                             List<List<T>> datas, String... fieldNames) throws Exception {
        //模板行
        Row rowTemplate = sheet.getRow(startRow);
        Object cellValue = null;
        int maxDatasSize = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                if (maxDatasSize < datas.get(i).size()) {
                    maxDatasSize = datas.get(i).size();
                }
                int j = 0;
                for (T t : datas.get(i)) {
                    for (int k = 0; k < fieldNames.length; k++) {
                        if (StrUtil.isNotEmpty(fieldNames[k])) {
                            cellValue = getFieldValue(t, fieldNames[k]);
                        }
                        setCellByValue(sheet, startRow + i, k + j + startCell, null, cellValue, rowTemplate);
                    }
                    j++;
                }
            }
        }
        refreshCellStyle(sheet, startRow, startRow + datas.size() - 1, startCell, startCell + maxDatasSize, null, rowTemplate);
    }

    /**
     * 用数据填充excel的cell
     * 仅接受String和Number格式的数据，非Number的数据，需转成String类型的
     *
     * @param sheet
     * @param rowNum  行号
     * @param cellNum 列号
     * @param value   cell的值
     * @return
     */
    public static void setCellByValue(Sheet sheet, int rowNum, int cellNum, Object value) {
        setCellByValue(sheet, rowNum, cellNum, null, value, null);
    }

    /**
     * 用数据填充excel的cell
     * 仅接受String和Number格式的数据，非Number的数据，需转成String类型的
     *
     * @param sheet
     * @param cellStyle cell样式
     * @param rowNum    行号
     * @param cellNum   列号
     * @param value     cell的值
     * @return
     */
    public static void setCellByValue(Sheet sheet, int rowNum, int cellNum, CellStyle cellStyle, Object value) {
        setCellByValue(sheet, rowNum, cellNum, cellStyle, value, null);
    }

    /**
     * 填充单元格
     *
     * @throws
     */
    private static void setCellByValue(Sheet sheet, int rowNum, int cellNum, CellStyle cellStyle, Object value, Row rowTemplate) {
        Cell cell = getCell(sheet, rowNum, cellNum, cellStyle, rowTemplate);
        if (value == null || StrUtil.isEmpty(value + "") || "null".equals(value + "")) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue("");
        } else {
            if (value instanceof String) {
                cell.setCellType(CellType.STRING);
                cell.setCellValue(value.toString());
            } else if (value instanceof Number) {
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(Double.parseDouble(value.toString()));
            } else if (value instanceof Date) {
//                cell.setCellType(CellType.STRING);
//                cell.setCellValue(DateUtil((Date) value,"yyyy-MM-dd"));
            }
        }
    }

    /**
     * 用公式+数据填充excel的cell
     *
     * @param sheet
     * @param rowNum  行号
     * @param cellNum 列号
     * @param value   cell的值
     * @return
     */
    public static void setCellByFormula(Sheet sheet, int rowNum, int cellNum, Object value) {
        setCellByFormula(sheet, rowNum, cellNum, null, value);
    }

    /**
     * 用公式+数据填充excel的cell
     *
     * @param sheet
     * @param cellStyle cell样式
     * @param rowNum    行号
     * @param cellNum   列号
     * @param value     cell的值
     * @return
     */
    public static void setCellByFormula(Sheet sheet, int rowNum, int cellNum, CellStyle cellStyle, Object value) {
        Cell cell = getCell(sheet, rowNum, cellNum, cellStyle, null);
        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula(value.toString());
    }

    /**
     * 获得cell的数值
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Object result = null;
        CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum) {
            case NUMERIC:
                result = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                if (result != null) {
                    result = result.toString();
                }
                break;
            case STRING:
                result = cell.getRichStringCellValue().getString();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            case ERROR:
                result = cell.getErrorCellValue();
                break;
            case BLANK:
                result = "";
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 移动行，
     *
     * @param startRow   开始移动的行号
     * @param endRow     结束移动的行号
     * @param shitRowNum 向下移动的行数
     * @throws
     */
    public static void shiftRows(Sheet sheet, int startRow, int endRow, int shitRowNum) {
        sheet.shiftRows(startRow, endRow, shitRowNum);
    }

    /**
     * 取得公式单元格的公式,重新设置,刷新公式值
     *
     * @throws
     */
    public static void refreshCellFormula(Sheet sheet) {
        for (Row eachRow : sheet) {
            for (Cell cell : eachRow) {
                if (CellType.FORMULA == cell.getCellTypeEnum()) {
                    cell.setCellFormula(cell.getCellFormula());
                }
            }
        }
    }

    /**
     * 刷新CellStyle
     */
    protected static void refreshCellStyle(Sheet sheet, int startRow, int endRow, int startCell, int endCell,
                                           CellStyle cellStyle, Row rowTemplate) {
        Row row = null;
        Cell cell = null;
        for (int i = startRow; i < endRow; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                for (int j = startCell; j < endCell; j++) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        cell = row.createCell(j);
                        if (cellStyle != null) {
                            cell.setCellStyle(cellStyle);
                        } else {
                            if (rowTemplate != null && rowTemplate.getCell(j) != null && rowTemplate.getCell(j).getCellStyle() != null) {
                                cell.setCellStyle(rowTemplate.getCell(j).getCellStyle());
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 获得cell
     *
     * @throws
     */
    private static Cell getCell(Sheet sheet, int rowNum, int cellNum, CellStyle cellStyle, Row rowTemplate) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(cellNum);
        if (cell == null) {
            cell = row.createCell(cellNum);
        }

        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
        if (rowTemplate != null) {
            Cell cellTemplata = rowTemplate.getCell(cellNum);
            if (cellTemplata != null && cellTemplata.getCellStyle() != null) {
                cell.setCellStyle(cellTemplata.getCellStyle());
            }
        }
        return cell;
    }

    /**
     * 合并单元格
     *
     * @param sheet
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    @SuppressWarnings("deprecation")
    public static void mergeCells(Sheet sheet, int x1, int x2, int y1, int y2) {
        sheet.addMergedRegion(new CellRangeAddress(x1, x2, y1, y2));
    }
    /**
     * 渲染excel到客户端
     * @throws
     */
    /*public static ResponseEntity renderExcel(Workbook wb,String fileName) throws Exception{
        if(!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx")){
			throw new Exception(fileName+"不是一个合法的excel文件名..............................");
		}
        HttpHeaders headers = new HttpHeaders();
        String downFileName = new String((fileName).getBytes(Constant.UTF8), Constant.ISO88591);
        headers.setContentDispositionFormData(Constant.ATTACHMENT, downFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        out = new FileOutputStream(file);

	    OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

    }*/

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter方法.
     */
    public static Object getFieldValue(final Object obj, final String fieldName) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }

        Object result = null;
        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {

        }
        return result;
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
     * <p>
     * 如向上转型到Object仍无法找到, 返回null.
     */
    public static Field getAccessibleField(final Object obj, final String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                makeAccessible(field);
                return field;
            } catch (NoSuchFieldException e) {//NOSONAR
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
     */
    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier
                .isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter方法.
     */
    public static String setFieldValue(final Object obj, final String fieldName, final Object value) {
        String flag = "";
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }

        Object type = field.getType();
        try {
            if (value != null && !"".equals((value + "").trim())) {
                if(type.equals(String.class)){
                    field.set(obj, value);
                }
                if(type.equals(Integer.class)){
                    field.set(obj, Integer.valueOf(value.toString()));
                }
                if(type.equals(BigDecimal.class)){
                    if(value.toString().matches("^(-?\\d+)(\\.\\d+)?$")){
                        field.set(obj, new BigDecimal(value.toString()));
                    }else{
                        flag = "TypeError";
                        return flag;
                    }
                }
            }
        } catch (IllegalAccessException e) {

        }
        return flag;
    }
    /**
     * 计算单元格公式(行求和)
     * @param initValue 开始从哪一列开始计算（对应的是字母的ascll值）
     * @param len 需要计算的单元格长度
     * @param rowNum 行数
     * @return
     */
    public static String sheetFormulaRowAdd(int initValue,int len,int rowNum){
        String start = "";
        String end = "";
        String res = "";
        if (len == 0){
            start = String.valueOf(((char)initValue));
            res = "=SUM("+start + rowNum+":"+start + rowNum+")" ;
        } else {
            start = String.valueOf(((char)initValue));
            end = String.valueOf(((char)(initValue + len)));
            res = "=SUM("+ start + rowNum + ":" + end + rowNum + ")";
        }
        return  res;
    }

    /**
     * 计算单元格公式(列求和)
     * @param initValue 开始从哪一列开始计算（对应的是字母的ascll值）
     * @param len 需要计算的单元格长度
     * @param cellNum 列数
     * @return
     */
    public static String sheetFormulaCellAdd(int initValue,int len,int cellNum){
        String start = "";
        String res = "";
        if (len == 0){
            start = String.valueOf(((char)initValue));
            res = "=SUM("+start + cellNum+":"+start + cellNum+")" ;
        } else {
            start = String.valueOf(((char)initValue));
            res = "=SUM("+ start + cellNum + ":" + start + (cellNum + len)+ ")";
        }
        return  res;
    }

    /**
     * 计算单元格公式(行求差)
     * @param initValue 开始从哪一列开始计算（对应的是字母的ascll值）
     * @param len 需要计算的单元格长度
     * @param rowNum 行数
     * @return
     */
    public static String sheetFormulaRowSub(int initValue,int len,int rowNum){
        String start = "";
        String end = "";
        String res = "";
        if (len == 0){
            start = String.valueOf(((char)initValue));
            res = "="+start + rowNum ;
        } else {
            start = String.valueOf(((char)initValue));
            end = String.valueOf(((char)(initValue + len)));
            res = "="+ start + rowNum + "-" + end + rowNum ;
        }
        return  res;
    }

}

