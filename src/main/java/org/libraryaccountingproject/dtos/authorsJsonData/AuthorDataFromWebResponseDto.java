package org.libraryaccountingproject.dtos.authorsJsonData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDataFromWebResponseDto {
    public String key;
    public String[] text;
    public String type;
    public String name;
    public String[] alternateNames = null;
    public String birthDate;
    public String topWork;
    public int work_count;
    public String[] topSubjects = null;
}
