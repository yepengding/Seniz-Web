package org.veritasopher.senizweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Source File
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceFile {

    private Long id;
    private String name;
    private Integer size;
    private String content;

}
