package org.libraryaccountingproject.dtos.authorsJSONDataModel;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AuthorDataFromWeb {

    public String key;
    public String[] text;
    public String type;
    public String name;
    public String[] alternate_names = null;
    public String birth_date;
    public String top_work;
    public int work_count;
    public String[] top_Subjects = null;
    public long _version_;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
