package com.ken.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ken
 * @date 2021-11-29 15:30
 * @since v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private Long id;
    private String info;
}

