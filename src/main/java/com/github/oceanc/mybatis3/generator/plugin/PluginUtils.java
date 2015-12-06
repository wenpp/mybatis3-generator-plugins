package com.github.oceanc.mybatis3.generator.plugin;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chengyang
 */
public abstract class PluginUtils {

    public static void addProperty(String field, FullyQualifiedJavaType fieldType, TopLevelClass topLevelClass, Context context, String tableName) {
        for (Method method : topLevelClass.getMethods()) {
            if (method.getName().equals("clear")) {
                method.addBodyLine("this." + field + " = null;");
            }
        }
        topLevelClass.addField(makeStringField(context, field, fieldType, tableName));
        topLevelClass.addMethod(makeGetterStringMethod(context, field, fieldType, tableName));
        topLevelClass.addMethod(makeSetterStringMethod(context, field, fieldType, tableName));
        System.out.println("-----------------" + topLevelClass.getType().getShortName() + " add field " + field + " and getter related.");
    }

    public static Field makeStringField(Context context, String fieldName, FullyQualifiedJavaType fieldType, String tableName) {
        Field field = new Field();
        field.setName(fieldName);
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(fieldType);
        addDoc(context, field, tableName);
        return field;
    }

    public static Method makeGetterStringMethod(Context context, String fieldName, FullyQualifiedJavaType fieldType, String tableName) {
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        Method method = new Method();
        method.setName(methodName);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(fieldType);
        method.addBodyLine("return this." + fieldName + ";");
        addDoc(context, method, tableName);
        return method;
    }

    public static Method makeSetterStringMethod(Context context, String fieldName, FullyQualifiedJavaType fieldType, String tableName) {
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        Method method = new Method();
        method.setName(methodName);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(fieldType, fieldName));
        method.addBodyLine("this." + fieldName + " = " + fieldName + ";");
        addDoc(context, method, tableName);
        return method;
    }

    public static void addDoc(Context context, JavaElement element, String tableName) {
        String suppressAllComments = context.getCommentGeneratorConfiguration().getProperty("suppressAllComments");
        if (!"true".equals(suppressAllComments)) {
            String type = element.getClass() == Field.class ? "field" : "method";
            element.addJavaDocLine("/**");
            element.addJavaDocLine("* This " + type + " was generated by MyBatis Generator.");
            element.addJavaDocLine("* This " + type + " corresponds to the database table " + tableName);
            element.addJavaDocLine("*");
            element.addJavaDocLine("* @mbggenerated " + df.format(new Date()));
            element.addJavaDocLine("*/");
        }
    }

    private static SimpleDateFormat df = new SimpleDateFormat("EEE MMM ww HH:mm:ss z yyyy", Locale.US);
}
