package com.asimkilic.loan.application.dto.phonebook;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "It is used to view the only phone number")
public class PhoneBookPartialDto {

    private String phone;
}
