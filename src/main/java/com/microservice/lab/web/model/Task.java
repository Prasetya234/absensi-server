package com.microservice.lab.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.lab.configuration.auditable.DateConfig;
import com.microservice.lab.web.enumated.TaskTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "note")
    private String note;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "assignment_date")
    private Date assignmentDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "due_date")
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskTypeEnum taskType;
    @Column(name = "is_closed")
    private Boolean isClosed;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "class_bootcamp_id")
    private School schoolId;
}
