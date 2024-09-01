package org.libraryaccountingproject.dtos.authorsJsonData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorJSONData {
    public String key;
    public String[] text;
    public String type;
    public String name;
    public String[] alternate_names = null;
    public String birth_date;
    public String top_work;
    public int work_count;
    public String[] top_subjects = null;

}
