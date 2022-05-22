package com.inno.accpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramDto {

    private List<TabDto> tabs;

    @Builder.Default
    private Boolean log = false;
}
