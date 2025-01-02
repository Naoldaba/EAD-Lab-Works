package com.itsc.exam.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Task {
    private int id;
    private String description;
    private String status;
    private Date dueDate;
}
