package com.tenpay.wxwork.salary.util.download;

import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author : treesen
 * @description :
 * @date : 2018/4/8
 */

public class ExcelDownloader{

    public int DOWNLOAD_REQS = 0;

    private Object mapper;

    private String methodName;

    private Class downloadBeanClass;

    private Map<String, Map> columnsInfo;

    private Object args;

    public ExcelDownloader(Object mapper, String methodName, Class downloadBeanClass){
        this.mapper = mapper;
        this.methodName = methodName;
        this.downloadBeanClass = downloadBeanClass;
    }

    public ExcelDownloader setColumnsInfo(Map<String, Map> columnsInfo) {
        this.columnsInfo = columnsInfo;
        return this;
    }

    public ExcelDownloader setArgs(Object args) {
        this.args = args;
        return this;
    }

    public void downloadByExplorer() throws Exception {
        download(new BufferedOutputStream( ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().getOutputStream() ));
    }

    public void download(OutputStream out) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        //数据来源方法
        Method targetMethod = null;
        //excel    application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
        SXSSFWorkbook  workbook = new SXSSFWorkbook(10000/(++DOWNLOAD_REQS));
        workbook.setCompressTempFiles(true);
        Sheet sheet = null;

        //分页
        int pageSize = 10000;//
        int page = 0;
        List<?> dataList = null;

        try{
            //数据来源方法
            targetMethod = mapper.getClass().getMethod(this.methodName, this.args.getClass());
            do{
                if(page % ( pageSize * 6 ) == 0){
                    //分sheet
                    sheet = workbook.createSheet(String.valueOf(workbook.getNumberOfSheets()));
                }
                //查询数据
                PageHelper.startPage( ++page, pageSize );
                dataList = (List<?>) targetMethod.invoke(this.mapper, this.args);
                //渲染excel
                renderWorkbook(workbook, sheet, dataList);
            }while (dataList.size() == pageSize);

            //导出
            workbook.write(out);

        } catch (Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            DOWNLOAD_REQS--;
            dataList = null;
            if(workbook != null){
                workbook.dispose();
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void renderWorkbook(SXSSFWorkbook workbook, Sheet sheet, List<?> dataList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(columnsInfo != null && columnsInfo.isEmpty() == false)
        {
            renderWorkbookWithColumnsInfo(workbook, sheet, dataList);
        }else{
            renderWorkbookWithDownloadBean(workbook, sheet, dataList);
        }
    }

    private void renderWorkbookWithColumnsInfo(SXSSFWorkbook workbook, Sheet sheet, List<?> dataList) {

    }

    private void renderWorkbookWithDownloadBean(SXSSFWorkbook workbook, Sheet sheet, List<?> dataList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Field[] declaredFields = this.downloadBeanClass.getDeclaredFields();
        Map<Integer, Integer> colsWidthMap = new HashMap<Integer, Integer>();
        for (int j = 0; j < dataList.size(); j++)
        {
            Row bodyRow = sheet.createRow( 1 + sheet.getLastRowNum());

            for (int i = 0; i < declaredFields.length; i++)
            {
                if(declaredFields[i].getAnnotation(ExcelDownloadAnnotation.class) != null)
                {
                    String fieldName = declaredFields[i].getName();
                    String cnName = declaredFields[i].getAnnotation(ExcelDownloadAnnotation.class).cnName();
                    int order = declaredFields[i].getAnnotation(ExcelDownloadAnnotation.class).order();
                    String format = declaredFields[i].getAnnotation(ExcelDownloadAnnotation.class).format();

                    Cell bodyCell = bodyRow.createCell(order - 1);
                    CellStyle style = workbook.createCellStyle();
                    DataFormat dataFormat = workbook.createDataFormat();
                    //excel header
                    if(sheet.getLastRowNum() == 1 && j == 0)
                    {
                        Row headRow = null;
                        if(i == 0){
                            sheet.createRow( 0);
                        }
                        headRow = sheet.getRow(0);
                        Cell headCell = headRow.createCell(order - 1);
                        String colShowName = (cnName == null || "".equals(cnName.trim())) ? fieldName : cnName;
                        headCell.setCellValue(colShowName);
                        try {
                            colsWidthMap.put(order - 1, colShowName.getBytes("UTF-8").length <= 15 ?  colShowName.getBytes("UTF-8").length : 15);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    //excel body
                    style.setDataFormat(dataFormat.getFormat(format));;
                    Object fieldValue = this.downloadBeanClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(dataList.get(j));

                    if(fieldValue instanceof String)
                    {
                        int valWidth = 0;
                        try {
                            valWidth = (String)fieldValue == null ? 10 : ((String)fieldValue).getBytes("UTF-8").length;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        int colWidth = colsWidthMap.get(order - 1) == null ? 10 : (colsWidthMap.get(order - 1) > 30 ? 30 : colsWidthMap.get(order - 1));

                        int lastWidth = (valWidth > colWidth && colWidth <= 30) ? valWidth : colWidth;
                        colsWidthMap.put(order - 1, lastWidth);
                        sheet.setColumnWidth(order - 1, lastWidth);
                        bodyCell.setCellValue((String)fieldValue);
                    }
                    else if(fieldValue instanceof Number)
                    {
                        colsWidthMap.put(order - 1, 20);
                        fieldValue = fieldValue == null ? "0" : fieldValue;
                        bodyCell.setCellValue(((Number) fieldValue).doubleValue());
                    }
                    else if (fieldValue instanceof Date)
                    {
                        colsWidthMap.put(order - 1, 20);
                        bodyCell.setCellValue((Date)fieldValue);
                    }
                    bodyCell.setCellStyle(style);
                }
            }
        }

        colWidthSetter(sheet, colsWidthMap);
    }
    //设置列宽
    private void colWidthSetter(Sheet sheet, Map<Integer, Integer> colsWidthMap){
       if(sheet != null && colsWidthMap != null)
       {
           Iterator<Integer> iterator = colsWidthMap.keySet().iterator();
           while(iterator.hasNext()){
               int key = iterator.next();
               int w = 140*(colsWidthMap.get(key))+184;
               sheet.setColumnWidth(key, w);
           }
       }
    }

}
