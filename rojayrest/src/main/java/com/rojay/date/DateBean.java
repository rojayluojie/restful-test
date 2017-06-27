package com.rojay.date;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * <p>功能描述,该部分必须以中文句号结尾。</p>
 * 创建日期 2017/6/21.
 * @author luojie(luojie@eefung.com)
 * @since $version$
 */
@XmlRootElement(name = "dateBean")
public class DateBean implements Serializable{
    private static final long serialVersionUID = 7985259985445615843L;
    private String dateFormat;
    private String dateString;
    public DateBean(){}


    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
