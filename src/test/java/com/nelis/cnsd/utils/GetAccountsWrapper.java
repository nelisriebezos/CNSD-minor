package com.nelis.cnsd.utils;

import com.nelis.cnsd.presentation.dto.response.GetBankAccountResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAccountsWrapper {
    private List<GetBankAccountResponse> accounts;
}
